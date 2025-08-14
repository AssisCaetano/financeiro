package com.controle.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class FinanceiroApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
		Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));
	}

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(FinanceiroApplication.class);
	    }
	 
}
