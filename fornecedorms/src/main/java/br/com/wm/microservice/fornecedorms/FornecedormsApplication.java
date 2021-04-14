package br.com.wm.microservice.fornecedorms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class FornecedormsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FornecedormsApplication.class, args);
	}

}
