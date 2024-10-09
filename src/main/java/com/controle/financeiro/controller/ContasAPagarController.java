package com.controle.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.service.ContasAPagarService;
import com.controle.financeiro.dto.ContasAPagarDto;

@RestController
@RequestMapping
public class ContasAPagarController {
    
    @Autowired
    private ContasAPagarService contasAPagarService;

    @PostMapping("/cadastro")
    public ResponseEntity<ContasAPagar> saveConta(@RequestBody ContasAPagarDto contasAPagar){
        return ResponseEntity.status(HttpStatus.CREATED).body(contasAPagarService.saveConta(contasAPagar));
    }
}
