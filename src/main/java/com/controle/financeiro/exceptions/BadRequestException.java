package com.controle.financeiro.exceptions;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(){
        super("Os campos preenchido já existem! ");
    }
    public BadRequestException(String message){
        super(message);
    }
}
