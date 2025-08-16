package com.controle.financeiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.financeiro.domain.model.Usuario;
import com.controle.financeiro.domain.service.UsuarioService;

@Controller
@RequestMapping
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	  @GetMapping("/login")
	    public String login(Usuario usuario) {
	        return "index"; // Página personalizada em src/main/resources/templates/
	    }
	  
		@GetMapping("cadastroLogin")
		public String exibir(Model model) {
			model.addAttribute("cadastro", new Usuario());
			return "login/cadastro";
		}
	  
	  @PostMapping("cadastroLogin")
	  public String cadastrarLogin(@ModelAttribute("cadastro")Usuario usuario, RedirectAttributes redirectAttributes) {
		// Obtém a senha em texto puro do formulário
			String plainPassword = usuario.getPassword();
			
			// Codifica a senha antes de salvar
			usuario.setUsername(usuario.getUsername());
			usuario.setPassword(passwordEncoder.encode(plainPassword));
		  	usuarioService.salvarUsuario(usuario);
		    redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado!");
		    return "redirect:/login"; // Redireciona para login
	  }
	  
	
}
