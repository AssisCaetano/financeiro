package com.controle.financeiro.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.PagamentoService;
import com.controle.financeiro.dto.PagamentoDto;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("/lancar/{id}")
    public ResponseEntity<Pagamento> lancarPagamento(@PathVariable(value = "id")UUID id, @RequestBody PagamentoDto pagamentoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.lancarPagamento(id, pagamentoDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Optional<Pagamento>> atualizaPagamento(@PathVariable(value = "id")UUID id, @RequestBody PagamentoDto pagamento){
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.atualizaPagamento(id, pagamento));
    }

    @GetMapping("/pagamentos")
    public ResponseEntity<List<Pagamento>> listaPagamentos(){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listaPagamentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Pagamento>> pagamentoId(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.pagamaentoId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> delatandoPagamento(@PathVariable(value = "id")UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.deletandoPagamento(id));
    }
}
