package br.com.foursales;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RestController
public class FoursalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoursalesApplication.class, args);
	}
	
	@RequestMapping("/home")
	public String foursalesHome() {
		return "Servidor de Aplicacao esta no ar!";
	}
	
}