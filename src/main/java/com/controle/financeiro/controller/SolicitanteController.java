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
import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.service.SolicitanteService;
import com.controle.financeiro.dto.SolicitanteDto;

@Controller
@RequestMapping("/cadastrar")
public class SolicitanteController {

	@Autowired
	private SolicitanteService solicitarService;

<<<<<<< HEAD
	//Exibe a home no navegador
	@GetMapping("/home")
	public String exibirHome() {
		return "home";
	}
	
=======
>>>>>>> 008e66adaf34901ab00fd9497471241fb956522a
	//Exibe o formulario no navegador
	@GetMapping("/cadastro")
	public String exibir(Model model) {
		model.addAttribute("cadastro", new Solicitante());
		return "cadastro-usuario/cadastro";
	}

	//Realiza o cadastro das informações no banco
	@PostMapping("/cadastro")
	public String cadastrarUsuario(@ModelAttribute("cadastro") SolicitanteDto solicitante) {
		solicitarService.salvarUsuario(solicitante);
		return "redirect:./lista";
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
		public String salvarAtualizarSolicitante(@PathVariable UUID id, @ModelAttribute("cadastro") SolicitanteDto solicitante, Model model) {
			Optional<Solicitante> cadastros = solicitarService.atualizaSolicitante(id, solicitante);
			model.addAttribute("cadastro", cadastros);
			
			return "redirect:/cadastrar/lista";
		}
		
		//Método responsável por excluir os dados do usuário
		@GetMapping("/delete/{id}")
		public String deletarSolicitante(@PathVariable UUID id) {
			solicitarService.deletaSolicitante(id);
			return "redirect:/cadastrar/lista";
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
	            System.out.println("Solicitante com ID " + id + " não encontrado para visualização.");
	            model.addAttribute("cadastro", new Solicitante());
	            model.addAttribute("contas", new java.util.ArrayList<ContasAPagar>());
	        }
	        return "cadastro-usuario/visualizar";
	    }
		
}
