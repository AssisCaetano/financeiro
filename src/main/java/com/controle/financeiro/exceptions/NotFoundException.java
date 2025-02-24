package com.controle.financeiro.exceptions;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(){
        super("Informação já existente no Banco! ");
    }

    public NotFoundException(String message){
        super(message);
    }
}
