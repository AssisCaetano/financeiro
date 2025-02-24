package com.controle.financeiro.exceptions;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(){
        super("O campo não pode está vazio! ");
    }
    public BadRequestException(String message){
        super(message);
    }
}
