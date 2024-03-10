package com.sistemaifes.sistemaifes.exception;

public class ItemAlreadyRegisteredException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ItemAlreadyRegisteredException(String name){
        super("Item já cadastrado no banco com o nome: " + name );
    }
}
