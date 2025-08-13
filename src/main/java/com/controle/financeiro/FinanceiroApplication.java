package com.controle.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FinanceiroApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
	
	}

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(FinanceiroApplication.class);
	    }
	 
}
