package com.mauris.energie.mgnt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mauris.energie.mgnt.ambrosusTemplate.RestAccess;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class MaurisMdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaurisMdwApplication.class, args);

	}

	@Bean
	CommandLineRunner testouille(RestAccess test) {
		return (arg)->{
			
			test.getSample();
		};
	}
}
