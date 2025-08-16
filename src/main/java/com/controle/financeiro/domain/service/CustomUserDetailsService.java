package com.controle.financeiro.domain.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.controle.financeiro.domain.model.Usuario;
import com.controle.financeiro.domain.repositore.UsuarioRepository;


public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username);

		Usuario usuario = optionalUsuario
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));

		return new User(usuario.getUsername(), usuario.getPassword(), Collections.emptyList());
	}
	


}
