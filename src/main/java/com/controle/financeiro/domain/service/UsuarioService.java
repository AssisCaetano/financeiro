package com.controle.financeiro.domain.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.Usuario;
import com.controle.financeiro.domain.repositore.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvarUsuario(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	
	public Optional<Usuario> usuarios(String username){
		return usuarioRepository.findByUsername(username);
	}
	
	 public Optional<Usuario> buscaUsuarios(Long id, Usuario usuario){	
	        return usuarioRepository.findById(id);
	    }
}
