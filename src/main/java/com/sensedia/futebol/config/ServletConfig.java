package com.sensedia.futebol.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * Created by renanpetronilho on 07/05/16.
 */
@Controller
public class ServletConfig {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			container.setPort(5050);
			container.setSessionTimeout(5000);
		});
	}
}
