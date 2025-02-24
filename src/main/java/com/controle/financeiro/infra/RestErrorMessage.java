package com.controle.financeiro.infra;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorMessage {
    
    private HttpStatus status;
    private String message;
}
