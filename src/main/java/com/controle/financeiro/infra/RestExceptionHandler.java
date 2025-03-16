package com.controle.financeiro.infra;


import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.controle.financeiro.exceptions.BadRequestException;
import com.controle.financeiro.exceptions.DataNotFound;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorMessage> NotFoundHandler(NotFoundException exception){
        RestErrorMessage teste = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(teste);
    }

    @ExceptionHandler(BadRequestException.class)
    private ResponseEntity<RestErrorMessage> BadRequestHandler(BadRequestException exception){
        RestErrorMessage dadoDuplicado = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dadoDuplicado);
    }

    @ExceptionHandler(DataNotFound.class)
    private ResponseEntity<RestErrorMessage> DataNotFoundHandler(DataNotFound exception){
        RestErrorMessage dadosNaoEncontrado = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dadosNaoEncontrado);
    }
}
