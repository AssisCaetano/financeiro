package com.controle.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.ui.Model;

@SpringBootApplication
public class FinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
	
	}

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(FinanceiroApplication.class);
	    }
}
