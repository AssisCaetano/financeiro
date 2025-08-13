package com.controle.financeiro.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.service.SolicitanteService;
import com.controle.financeiro.dto.SolicitanteDto;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cadastrar")
public class SolicitanteController {

	@Autowired
	private SolicitanteService solicitarService;

	//Exibe a home no navegador
	@GetMapping("/home")
	public String exibirHome() {
		return "home";
	}
	
	//Exibe o formulario no navegador
	@GetMapping("/cadastro")
	public String exibir(Solicitante solicitante, Model model) {
		model.addAttribute("cadastro", new Solicitante());
		return "cadastro-usuario/cadastro";
	}

	//Realiza o cadastro das informações no banco
	@PostMapping("/cadastro")
	public String cadastrarUsuario(RedirectAttributes attr, @Valid @ModelAttribute("cadastro")SolicitanteDto solicitante, BindingResult result) {
		
		//VERIFICANDO SE O CAMPO É UM CPF VALIDO
		if(result.hasErrors()) {
			attr.addFlashAttribute("fail", "Erro na validação do campo CPF");
			attr.addFlashAttribute("org.springframework.validation.BindingResult.cadastro", result);
			attr.addFlashAttribute("cadastro", solicitante);
			return "redirect:./cadastro";
		}
		//REALIZA O SALVAMENTO DA INFOMAÇÃO E CASO ACONTEÇA ALGUM ERRO LANÇA UM EXCESSÃO
		try {
			solicitarService.salvarUsuario(solicitante);
			attr.addFlashAttribute("success", "Dados cadastrado com sucesso.");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar as informaçõe. " + e.getMessage());
			attr.addFlashAttribute("fail", "O CPF informado para o cadastro já existe em nossa BASE.");
		}
		return "redirect:./cadastro";
	}

	//Lista todos os dados do banco
	@GetMapping("/lista")
	public String listaSolicitante(Model model) {
		model.addAttribute("lista", solicitarService.listarSolicitante());
		return "cadastro-usuario/lista";
	}
	
	// Esse método é responsavél por enviar os dados para os campos de cadastro
		@GetMapping("/cadastro/{id}")
		public String atualizarSolicitante(@PathVariable UUID id, SolicitanteDto solicitante, Model model) { 
			Optional<Solicitante> solicitar = solicitarService.buscaSolicitante(id, solicitante);	
			model.addAttribute("cadastro", solicitar);
			return "cadastro-usuario/cadastro";
		}
		
		//Método responsável por realizar a atualização das informações
		@PostMapping("/cadastro/{id}")
		public String salvarAtualizarSolicitante(@PathVariable UUID id, RedirectAttributes attr, SolicitanteDto solicitante, Model model) {
			Optional<Solicitante> cadastros = solicitarService.atualizaSolicitante(id, solicitante);
			try {
				model.addAttribute("cadastro", cadastros);
				attr.addFlashAttribute("success", "Solicitante atualizado com sucesso. ");
			} catch (Exception e) {
				System.out.println("Dados não atualizado " + e.getMessage());
				attr.addFlashAttribute(
						"fail", "Dados não atualizado pois o CPF informado já existe na Base de Dados com "
								+ "outro solicitante. "
								+ solicitante.cpf()
						);

			}
			return "redirect:/cadastrar/cadastro";
		}
		
		//Método responsável por excluir os dados do usuário
		@GetMapping("/delete/{id}")
		public String deletarSolicitante(@PathVariable UUID id, RedirectAttributes attr) {
			
			try {
				solicitarService.deletaSolicitante(id);
				attr.addFlashAttribute("success", "Solicitante excluído com sucesso. ");
				return "redirect:/cadastrar/lista";
			} catch (Exception e) {
				attr.addFlashAttribute("fail", "Erro ao Excluír solicitante!, Existe um registro associado.");
				return "redirect:/cadastrar/lista";	
			}
		}
		
		// Responsavel por exibir o detalhamento da conta
	    // Modificado para carregar Solicitante e TODAS as ContasAPagar associadas
	    @GetMapping("/visualizar/{id}")
	    public String exibirRegistro(@PathVariable("id") UUID id, Model model) {
	        Optional<Solicitante> solicitanteOptional = solicitarService.buscaSolicitanteComContas(id);

	        if (solicitanteOptional.isPresent()) {
	            Solicitante solicitante = solicitanteOptional.get();
	            model.addAttribute("cadastro", solicitante);
	            model.addAttribute("contas", solicitante.getContasAPagar());
	        } else {
	            // Tratar caso o solicitante não seja encontrado
	            
	            model.addAttribute("cadastro", new Solicitante());
	            model.addAttribute("contas", new java.util.ArrayList<ContasAPagar>());
	        }
	        return "cadastro-usuario/visualizar";
	    }
	    
	    @GetMapping("/buscar/nome")
	    public String buscarPorNome(@RequestParam("nome")String nome, Model model) {
	    	model.addAttribute("lista", solicitarService.buscarPorNome(nome));
	    	return "cadastro-usuario/lista";
	    }
		
}
