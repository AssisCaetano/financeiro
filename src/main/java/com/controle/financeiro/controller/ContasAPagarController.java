package com.controle.financeiro.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.service.ContasAPagarService;
import com.controle.financeiro.dto.ContasAPagarDto;

@RestController
@CrossOrigin("*")
@RequestMapping("/cadastrar_conta/")
public class ContasAPagarController {
    
    @Autowired
    private ContasAPagarService contasAPagarService;

    @PostMapping("conta")
    public ResponseEntity<ContasAPagar> saveConta(@RequestBody ContasAPagarDto contasAPagar){
        return ResponseEntity.status(HttpStatus.CREATED).body(contasAPagarService.saveConta(contasAPagar));
    }

    @GetMapping("contas")
    public ResponseEntity <List<ContasAPagar>> getContasAPagarAll(){
        return ResponseEntity.status(HttpStatus.OK).body(contasAPagarService.getContasAPagarAll());
    }

    @GetMapping("conta/{id}")
    public ResponseEntity<Optional<ContasAPagar>> getContasAPagarAll(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(contasAPagarService.getContasAPagarById(id));
    }

    @PutMapping("conta/{id}")
    public ResponseEntity <Optional<ContasAPagar>> atualizarContasAPagar(@PathVariable(value = "id")UUID id, @RequestBody ContasAPagarDto contasAPagar){
        return ResponseEntity.status(HttpStatus.CREATED).body(contasAPagarService.atualizarContasAPagar(id, contasAPagar));
    }

    @DeleteMapping("conta/{id}")
    public ResponseEntity<Optional<ContasAPagar>> deletarConta(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(contasAPagarService.deletarConta(id));
    }
}
