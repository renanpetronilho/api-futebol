package com.sensedia.futebol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by renanpetronilho on 09/05/16.
 */
@Configuration
@EnableSwagger2
public class ApiDocumentationConfiguration extends WebMvcConfigurerAdapter {

	public static final String[] WEB_JAR_RESOURCE_PATTERNS = {"css/", "images/", "lib/", "swagger-ui.js"};
	public static final String WEB_JAR_RESOURCE_LOCATION = "classpath:META-INF/resources/";
	public static final String WEB_JAR_VIEW_RESOLVER_PREFIX = "classpath:/resources/";
	public static final String WEB_JAR_VIEW_RESOLVER_SUFFIX = ".html";

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(WEB_JAR_RESOURCE_PATTERNS)
				.addResourceLocations(WEB_JAR_RESOURCE_LOCATION).setCachePeriod(0);
	}

	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(WEB_JAR_VIEW_RESOLVER_PREFIX);
		resolver.setSuffix(WEB_JAR_VIEW_RESOLVER_SUFFIX);
		return resolver;
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.paths(Predicates.not(PathSelectors.regex("/error.*")))
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API Futebol")
				.description("API que fornece dados sobre futebol.")
				.termsOfServiceUrl("https://github.com/renanpetronilho")
				.contact("@renanpetronilho")
				.version("1.0")
				.build();
	}


	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
