package com.controle.financeiro.exceptions;

public class DataNotFound extends RuntimeException{
    public DataNotFound(){
        super("Dados não encontrado! ");
    }

    public DataNotFound(String message){
        super(message);
    }
}
