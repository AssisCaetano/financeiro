package com.controle.financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.boot.builder.SpringApplicationBuilder;
=======
>>>>>>> 008e66adaf34901ab00fd9497471241fb956522a
import org.springframework.ui.Model;

@SpringBootApplication
public class FinanceiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceiroApplication.class, args);
<<<<<<< HEAD
=======
		
>>>>>>> 008e66adaf34901ab00fd9497471241fb956522a
	
	}

	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(FinanceiroApplication.class);
	    }
}
