package com.testsquad.crud.modules.editalmanagement.application.exception;

public class EditalNotFoundException extends RuntimeException {

    public EditalNotFoundException(String id) {
        super("Edital não encontrado: " + id);
    }
}
