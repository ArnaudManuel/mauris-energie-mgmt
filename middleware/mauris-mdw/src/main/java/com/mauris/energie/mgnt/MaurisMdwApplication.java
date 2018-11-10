package com.mauris.energie.mgnt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MaurisMdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaurisMdwApplication.class, args);
	}

	@Bean
	CommandLineRunner printValue(){
		return (CommandLineRunner) -> {

		};
	}
}
