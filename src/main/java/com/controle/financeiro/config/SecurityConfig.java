package com.controle.financeiro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.controle.financeiro.domain.repositore.UsuarioRepository;
import com.controle.financeiro.domain.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // URLs públicas
                .requestMatchers("/login").permitAll() // Páginas públicas
//                .requestMatchers("/cadastroLogin").permitAll() // Páginas públicas
                
             // URLs Protegidas (somente usuários logados podem acessar)
                .requestMatchers("/**").authenticated()
                .requestMatchers("/cadastroLogin").authenticated()
//                .requestMatchers("/cadastrar/cadastro/{id}**").authenticated()
//                .requestMatchers("/contas/**").authenticated()
//                .requestMatchers("/cadastrar_pagamento/**").authenticated()
//                .requestMatchers("/novo_credito/**").authenticated()
                
                .anyRequest().authenticated()// Todas as outras requerem autenticação
            )
            .formLogin(form -> form
                .loginPage("/login")
                .failureUrl("/login?error")
                .defaultSuccessUrl("/cadastrar/home", true) // Redireciona após login
                .permitAll()
            )
            .logout(logout -> logout
            		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Garante que a requisição de logout seja sempre um POST ou GET
            		.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true) // Invalida a sessão HTTP do usuário
                	.deleteCookies("JSESSIONID") // Deleta o cookie de sessão do navegador
            		.permitAll()
            )
        	
        	.sessionManagement(session -> session
                .maximumSessions(1) // Permite apenas 1 sessão por usuário
                .maxSessionsPreventsLogin(true) // Impede novo login se a sessão já existe
                .expiredUrl("/login?expired") // Redireciona para o login com um parâmetro
            )
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
	
	@Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
	}					
	@Bean
	public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
	    CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();
        customUserDetailsService.setUsuarioRepository(usuarioRepository);
        return customUserDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder(); // Usa BCrypt para senhas
	}

}
