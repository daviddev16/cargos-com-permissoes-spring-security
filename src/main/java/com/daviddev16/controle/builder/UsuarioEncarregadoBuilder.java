package com.daviddev16.controle.builder;

import com.daviddev16.annotation.UsoInterno;
import com.daviddev16.controle.dto.UsuarioEncarregado;
import com.daviddev16.controle.entidade.Usuario;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@UsoInterno("Deve ser usado apenas para testar a criação de usuário")
public final class UsuarioEncarregadoBuilder {

    private Usuario usuario;
    private Set<String> nomeDosCargos;

    public static UsuarioEncarregadoBuilder builder() {
        return new UsuarioEncarregadoBuilder();
    }

    private UsuarioEncarregadoBuilder() {}

    public UsuarioEncarregadoBuilder usuarioSimples(String login, String nome, String senha) {

        Objects.requireNonNull(login, "login não pode ser nulo.");
        Objects.requireNonNull(nome, "nome não pode ser nulo.");
        Objects.requireNonNull(senha, "senha não pode ser nulo.");

        this.usuario = Usuario
                .builder()
                    .nome(nome)
                    .login(login)
                    .senha(senha)
                .build();

        return this;
    }

    public UsuarioEncarregadoBuilder usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public UsuarioEncarregadoBuilder usuarioBuilder(Usuario.UsuarioBuilder usuarioBuilder) {
        Objects.requireNonNull(usuarioBuilder, "usuarioBuilder não pode ser nulo.");
        usuario(usuarioBuilder.build());
        return this;
    }

    public UsuarioEncarregadoBuilder cargos(String... nomes) {
        Objects.requireNonNull(nomes, "nomes não pode ser nulo.");
         nomeDosCargos = new HashSet<>(List.of(nomes));
         return this;
    }

    public UsuarioEncarregado build() {
        Objects.requireNonNull(usuario, "O objeto do usuário não foi informando.");

        if (nomeDosCargos == null || nomeDosCargos.isEmpty())
            throw new IllegalStateException("É necessário informar ao menos 1 cargo para usuário.");

        return new UsuarioEncarregado(usuario, nomeDosCargos);
    }

}
