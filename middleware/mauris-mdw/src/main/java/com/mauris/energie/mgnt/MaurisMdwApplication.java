package com.mauris.energie.mgnt;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.mauris.energie.mgnt.ambrosusTemplate.RestAccess;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class MaurisMdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaurisMdwApplication.class, args);

	}

	@Bean
	CommandLineRunner testouille(RestAccess test) {
		return (arg)->{

            System.out.println(test.getAmbrosus("CH1014001234500000000000000006860_test", new Date(1L), new Date()));
		};
	}
}
