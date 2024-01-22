package com.daviddev16.controle.exception;

import static java.lang.String.*;

public final class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String login) {
        super( format("Login '%s' não encontrado.", login) );
    }
}
