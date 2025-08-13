package com.controle.financeiro.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.service.ContasAPagarService;
import com.controle.financeiro.domain.service.SolicitanteService;
import com.controle.financeiro.dto.ContasAPagarDto;


@Controller
@RequestMapping("/contas")
public class ContasAPagarController {
    
    @Autowired
    private ContasAPagarService contasAPagarService;
    
    @Autowired
    private SolicitanteService solicitanteService;
    
   
    
    //Exibe o formulario para preenchimento
    @GetMapping("/conta")
    public String exibiFormularioConta(Model model) {
    	model.addAttribute("conta", new ContasAPagar());
    	model.addAttribute("usuarios", solicitanteService.listarSolicitante());
    	return "cadastro-conta/contasAPagar";
    }

    //Realiza a inserção dos dados no bd
    @PostMapping("/conta")
    public String saveConta(RedirectAttributes attr,@ModelAttribute("conta") ContasAPagar contasAPagar) {
    	
//    	if(result.hasErrors()) {
//    		attr.addFlashAttribute("org.springframework.validation.BindingResult.cadastro", result);
//    	}
    	contasAPagarService.saveConta(contasAPagar);
    	attr.addFlashAttribute("success", "Conta associado ao solicitante com sucesso.");
		return "redirect:./conta";
	}
    
    //Método responsável por exibir a lista de contas
    @GetMapping("/lista")
    public String listaContasAPagar(Model model) {
    	List<ContasAPagar> contas = contasAPagarService.getContasAPagarAll();
    	model.addAttribute("lista", contas);
    	return "cadastro-conta/lista";
    }

    
    //Método responsável por exibir informações nos campos para edição
    @GetMapping("/conta/{id}")
    public String atualizarContaPorId(@PathVariable UUID id, ContasAPagarDto contasAPagar, Model model ) {
    	Optional<ContasAPagar> conta = contasAPagarService.getContasAPagarById(id, contasAPagar);
    	model.addAttribute("conta", conta);
    	return "cadastro-conta/contasAPagar";
    }

    
	//Método responsável por realizar a atualização das informações
	@PostMapping("/conta/{id}")
	public String salvarAtualizarConta(@PathVariable UUID id, ContasAPagarDto contasAPagar, RedirectAttributes attr, Model model) {
		Optional<ContasAPagar> conta = contasAPagarService.atualizarContasAPagar(id, contasAPagar);
		try {
			model.addAttribute("conta", conta);
			attr.addFlashAttribute("success", "Conta Atualizada com sucesso.");
			
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Conta Atualizada com sucesso.");
			
		}

		return "redirect:/contas/conta";
	}

	//Método responsável por excluir um conta
	@GetMapping("/delete/{id}")
	public String deletarConta(@PathVariable UUID id, RedirectAttributes attr) {	
		try {
			contasAPagarService.deletarConta(id);
			attr.addFlashAttribute("success", "Conta excluída com sucesso! ");
			return "redirect:/contas/lista";
		} catch (Exception e) {
			attr.addFlashAttribute("fail", "Erro ao Excluír conta!, Existe um Crédito ou um Pagamento associado à essa CONTA.");
			return "redirect:/contas/lista";	
		}		
	}
	
	//Recebendo uma lista de usuários
	@ModelAttribute("usuarios")
	public List<Solicitante> listaDeSolicitante(){
		return solicitanteService.listarSolicitante();
	}
	
	//BUSCAR CONTA ATRAVES DO SOLICITANTE
	@GetMapping("/buscar/nome")
	public String buscarContaPorSolicitante(@RequestParam("nome")String nome, Model model) {
		model.addAttribute("lista", contasAPagarService.buscarContaPorSolicitante(nome));
		return "cadastro-conta/lista";
	}

}
