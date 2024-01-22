package com.daviddev16.controle.dto;

import com.daviddev16.annotation.UsoInterno;
import com.daviddev16.controle.entidade.Usuario;

import java.util.Set;

@UsoInterno
public final class UsuarioEncarregado {

    private final Usuario usuario;
    private final Set<String> nomeDosCargos;

    public UsuarioEncarregado(Usuario usuario, Set<String> nomeDosCargos) {
        this.usuario = usuario;
        this.nomeDosCargos = nomeDosCargos;
    }

    public Set<String> getNomeDosCargos() {
        return nomeDosCargos;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
