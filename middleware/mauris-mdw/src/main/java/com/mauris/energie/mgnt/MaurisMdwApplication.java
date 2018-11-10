package com.mauris.energie.mgnt;

import com.mauris.energie.mgnt.ambrosusTemplate.EventTemplate;
import com.mauris.energie.mgnt.ambrosusTemplate.RestAccess;
import com.mauris.energie.mgnt.mappers.TemplateConverter;
import com.mauris.energie.mgnt.model.History;
import com.mauris.energie.mgnt.services.PodService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class MaurisMdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaurisMdwApplication.class, args);
	}

	@Bean
	CommandLineRunner restTest(RestAccess test) {
		return (arg)->{
			EventTemplate data = test.getAmbrosus("CH1014001234500000000000000006860_test", new Date(1L), new Date());
            System.out.println(data.toString());

			History history = TemplateConverter.toHistory(data);

			System.out.println(history.toString());
		};
	}

}
