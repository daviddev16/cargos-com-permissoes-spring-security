package com.daviddev16.controle.exception;

import com.daviddev16.core.RuntimeServiceException;

public class InvalidRequestStateException extends RuntimeServiceException {

    public InvalidRequestStateException(String message) {
        super(message);
    }

}
