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
import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.service.SolicitanteService;
import com.controle.financeiro.dto.SolicitanteDto;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("http://127.0.0.1:5500/")
@RequestMapping("/cadastrar_usuario/")
public class SolicitanteController {

    @Autowired
    private SolicitanteService solicitarService;
    
    @PostMapping("usuario")
    public ResponseEntity<Solicitante> salvarUsuario(@Valid @RequestBody SolicitanteDto solicitante){
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitarService.salvarUsuario(solicitante));
    }

    @PutMapping("usuario/{id}")
    public ResponseEntity <Optional<Solicitante>> atualizaSolicitante(@PathVariable(value = "id")UUID id, @RequestBody SolicitanteDto solicitante){
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitarService.atualizaSolicitante(id, solicitante));
    }

    @GetMapping("usuarios")
    public ResponseEntity<List<Solicitante>> listaSolicitante(){
        return ResponseEntity.status(HttpStatus.OK).body(solicitarService.listarSolicitante());
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<Optional<Solicitante>> buscaSolicitante(@PathVariable (value = "id")UUID id, SolicitanteDto solicitanteDto){
        return ResponseEntity.status(HttpStatus.OK).body(solicitarService.buscaSolicitante(id, solicitanteDto));
    }

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<Optional<Solicitante>> deletaSolicitante(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(solicitarService.deletaSolicitante(id));
    }

    @PostMapping("/conta/{id}")
    public ResponseEntity<Solicitante> adicionaConta(@PathVariable(value = "id")UUID id, @RequestBody ContasAPagar contasAPagar){
        // Solicitante adConta = solicitarService.adicionaConta(id, contasAPagar);
        return ResponseEntity.status(HttpStatus.OK).body(solicitarService.adicionaConta(id, contasAPagar));
    }

    @PutMapping("/remover/{id}")
    public ResponseEntity<Solicitante> removerConta(@PathVariable(value = "id")UUID id, ContasAPagar contasAPagar){
        return ResponseEntity.status(HttpStatus.OK).body(solicitarService.removerConta(id, contasAPagar));
    }
}
