package com.sensedia.futebol;

import com.sensedia.futebol.config.ApiDocumentationConfiguration;
import com.sensedia.futebol.repository.TeamRepository;
import com.sensedia.futebol.model.Team;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ApiFutebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiFutebolApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TeamRepository repository) {
		return (args) -> {
			repository.save(new Team("Palmeiras","PAL"));
			repository.save(new Team("Santos","SAN"));
		};
	}

	@Bean
	public Swagger2DocumentationConfiguration swagger2Config(){
		return new springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration();
	}

	@Bean
	public ApiDocumentationConfiguration springConfig(){
		return new ApiDocumentationConfiguration();
	}
}
