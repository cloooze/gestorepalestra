package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:webconfig.properties")
//@ConfigurationProperties(prefix = "mail")
public class WebConfig {
	
	private String labelNome;

	public String getLabelNome() {
		return labelNome;
	}

	public void setLabelNome(String labelNome) {
		this.labelNome = labelNome;
	}
	
	

}
