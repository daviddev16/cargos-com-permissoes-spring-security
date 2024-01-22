package com.daviddev16.controle.impl;

import com.daviddev16.config.PasswordConfig;
import com.daviddev16.controle.Permissao;
import com.daviddev16.controle.dao.CargoPermissaoRepository;
import com.daviddev16.controle.dao.CargoUsuarioRepository;
import com.daviddev16.controle.dao.UsuarioRepository;
import com.daviddev16.controle.dto.UsuarioEncarregado;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.CargoPermissao;
import com.daviddev16.controle.entidade.CargoUsuario;
import com.daviddev16.controle.entidade.Usuario;
import com.daviddev16.controle.exception.InvalidRequestStateException;
import com.daviddev16.controle.security.CustomUsuarioDetails;
import com.daviddev16.controle.service.CargoPermissaoService;
import com.daviddev16.controle.service.CargoService;
import com.daviddev16.controle.service.CargoUsuarioService;
import com.daviddev16.controle.service.UsuarioService;
import com.daviddev16.controle.exception.UsuarioNaoEncontradoException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final CargoPermissaoService cargoPermissaoService;
    private final CargoUsuarioService cargoUsuarioService;
    private final CargoService cargoService;
    private final PasswordConfig passwordConfig;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, CargoPermissaoService cargoPermissaoService,
                              CargoUsuarioService cargoUsuarioService, CargoService cargoService,
                              PasswordConfig passwordConfig)
    {
        this.usuarioRepository = usuarioRepository;
        this.cargoPermissaoService = cargoPermissaoService;
        this.cargoUsuarioService = cargoUsuarioService;
        this.cargoService = cargoService;
        this.passwordConfig = passwordConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Usuario usuario = obterUsuarioPorId(username);
        Set<GrantedAuthority> autoridadesDoUsuario = obterAutoridadesDeUsuario(usuario);

        return new CustomUsuarioDetails(usuario, autoridadesDoUsuario);

    }

    @Override
    @Transactional
    public Usuario criarUsuarioDeUsuarioEncarregado(final UsuarioEncarregado usuarioEncarregado) {

        Set<String> nomeDosCargos = usuarioEncarregado.getNomeDosCargos();
        Usuario novoUsuario = criarUsuario(usuarioEncarregado.getUsuario());

        List<Cargo> cargosParaUsuario = cargoService.obterCargoPorNomes(nomeDosCargos);

        cargosParaUsuario.forEach(cargo ->
                cargoUsuarioService.adicionarCargoAUsuario(novoUsuario, cargo));

        return novoUsuario;

    }

    private Set<GrantedAuthority> obterAutoridadesDeUsuario(final Usuario usuario) {

        List<Cargo> cargosDoUsuario = cargoUsuarioService.obterCargosDeUsuario(usuario);

        if (cargosDoUsuario.isEmpty())
            throw new InvalidRequestStateException( format("Usuário '%s' não possui " +
                    "nenhum cargo/perfil definido.", usuario.getLogin()));

        return converterCargoEmAutoridades(cargosDoUsuario);

    }

    private Set<GrantedAuthority> converterCargoEmAutoridades(final List<Cargo> cargosDoUsuario) {

        Set<GrantedAuthority> autoridades = new HashSet<>();

        for (Cargo cargo : cargosDoUsuario) {
            autoridades.add(new SimpleGrantedAuthority(cargo.getNome()));

            /* Permissão também devem ser autoridades */
            for (CargoPermissao permissao : cargo.getCargoPermissoes()) {
                autoridades.add(new SimpleGrantedAuthority(permissao.getPermissao().name()));
            }

        }

        return autoridades;
    }

    @Override
    public Set<Permissao> obterPermissoesDoUsuario(Usuario usuario) {
        Set<Permissao> permissoes = new HashSet<>();

        for (Cargo cargo : cargoUsuarioService.obterCargosDeUsuario(usuario)) {
            cargo.getCargoPermissoes()
                    .forEach(cargoPermissao -> permissoes.add(cargoPermissao.getPermissao()));
        }

        return permissoes;
    }

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(passwordConfig.passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obterUsuarioPorId(String login) {
        return usuarioRepository
                .findByLogin(login)
                .orElseThrow(usuarioNaoEncontradoSupplier(login));
    }

    private Supplier<UsuarioNaoEncontradoException> usuarioNaoEncontradoSupplier(String login) {
        return () -> new UsuarioNaoEncontradoException(login);
    }

    @Override
    public UserDetailsService obterUserDetailsService() {
        /* Auto referência para isolar o UserDetailsService do UsuarioService */
        return this;
    }
}
