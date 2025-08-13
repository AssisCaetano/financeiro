package com.controle.financeiro.controller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.service.ContasAPagarService;
import com.controle.financeiro.domain.service.PagamentoService;
import com.controle.financeiro.dto.ContasAPagarDto;

import com.controle.financeiro.dto.PagamentoDto;

@Controller
@RequestMapping("/cadastrar_pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;
    
    @Autowired
    private ContasAPagarService contasAPagarService;
    
    @GetMapping("/cadastro/{id}")
    public String exibiFormulario(@PathVariable("id")UUID id, ContasAPagarDto contasAPagar, Model model) {
    	model.addAttribute("cadastro", new Pagamento());
    	Optional<ContasAPagar> pagar = contasAPagarService.getContasAPagarById(id, contasAPagar);
    	model.addAttribute("conta", pagar);
    	return "pagamento/cadastro";
    }
    
    @PostMapping("/cadastro/{id}")
    public String salvar(RedirectAttributes attr,@PathVariable("id")UUID id, @ModelAttribute("cadastro") PagamentoDto pagamento) {
    	
    	pagamentoService.lancarPagamento(id, pagamento);
    	attr.addFlashAttribute("success", "Pagamento registrado com sucesso.");
    	return "pagamento/cadastro";
    	
        }
    	
		// LISTANDO OS CRÉDITOS ADICIONAIS
		@GetMapping("/lista")
		public String lista(Model model) {
			List<Pagamento> pg = pagamentoService.listaPagamentos();
			model.addAttribute("lista", pg);
			return "pagamento/lista";
			
//    @PostMapping("pagamento/{id}")
//    public ResponseEntity<Pagamento> lancarPagamento(@PathVariable(value = "id")UUID id, @RequestBody PagamentoDto pagamentoDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.lancarPagamento(id, pagamentoDto));
//    }
//
//    @PutMapping("pagamento/{id}")
//    public ResponseEntity <Optional<Pagamento>> atualizaPagamento(@PathVariable(value = "id")UUID id, @RequestBody PagamentoDto pagamento){
//        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.atualizaPagamento(id, pagamento));
//    }
//
//    @GetMapping("pagamentos")
//    public ResponseEntity<List<Pagamento>> listaPagamentos(){
//        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listaPagamentos());
//    }
//
//    @GetMapping("pagamento/{id}")
//    public ResponseEntity <Optional<Pagamento>> pagamentoId(@PathVariable(value = "id")UUID id){
//        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.pagamaentoId(id));
//    }
//
//    @DeleteMapping("pagamento/{id}")
//    public ResponseEntity<Optional<Pagamento>> delatandoPagamento(@PathVariable(value = "id")UUID id){
//        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.deletandoPagamento(id));
//    }
    }
}
