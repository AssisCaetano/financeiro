package com.controle.financeiro.exceptions;

public class NotFoundException extends RuntimeException{
    
    public NotFoundException(){
        super("Preencha todos os campos! ");
    }

    public NotFoundException(String message){
        super(message);
    }
}
