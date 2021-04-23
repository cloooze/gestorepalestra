package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;


//@Configuration
public class ThymeleafConfig {
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
	    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//	    resolver.setTemplateEngine(templateEngine());
	    resolver.setCharacterEncoding("UTF-8");
	    return resolver;
	}

}
