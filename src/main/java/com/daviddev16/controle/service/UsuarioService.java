package com.daviddev16.controle.service;

import com.daviddev16.controle.Permissao;
import com.daviddev16.controle.dto.UsuarioEncarregado;
import com.daviddev16.controle.entidade.Cargo;
import com.daviddev16.controle.entidade.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

public interface UsuarioService {

    Usuario obterUsuarioPorId(String login);

    UserDetailsService obterUserDetailsService();

    Usuario criarUsuarioDeUsuarioEncarregado(final UsuarioEncarregado usuarioEncarregado);

    Set<Permissao> obterPermissoesDoUsuario(Usuario usuario);

}
