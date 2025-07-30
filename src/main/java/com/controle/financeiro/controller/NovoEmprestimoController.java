package com.controle.financeiro.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.NovoEmprestimo;
import com.controle.financeiro.domain.service.ContasAPagarService;
import com.controle.financeiro.domain.service.NovoEmprestimoService;
import com.controle.financeiro.dto.ContasAPagarDto;
import com.controle.financeiro.dto.NovoEmprestimoDto;


@Controller
@RequestMapping("/novo_credito")
public class NovoEmprestimoController {
    @Autowired
    private NovoEmprestimoService novoEmprestimoService;
    
    @Autowired
    private ContasAPagarService contasAPagarService;
    
    @GetMapping("/cadastro/{id}")
    public String exibiFormulario(@PathVariable("id")UUID id, ContasAPagarDto contasAPagar, Model model) {
    	model.addAttribute("cadastro", new NovoEmprestimo());
    	Optional<ContasAPagar> associar = contasAPagarService.getContasAPagarById(id, contasAPagar);
    	model.addAttribute("conta", associar);
    	return "emprestimo/cadastro";
    }
    
    @PostMapping("/cadastro/{id}")
    public String salvar(@PathVariable("id")UUID id, @ModelAttribute("cadastro") NovoEmprestimoDto novoEmprestimo) {
    	novoEmprestimoService.saveEmprestimo(id, novoEmprestimo);
    	return "emprestimo/cadastro";
    }
    
}
