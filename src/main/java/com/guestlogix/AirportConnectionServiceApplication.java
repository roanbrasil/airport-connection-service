package com.guestlogix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class AirportConnectionServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(AirportConnectionServiceApplication.class, args);
	}
}
