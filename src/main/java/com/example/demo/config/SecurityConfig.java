package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private NegotiateSecurityFilter filter;
	private NegotiateSecurityFilterEntryPoint entryPoint;
	
	/**
	 * Autowire constructor injects bean auto-configured by Starter.
	 *
	 */
	public SecurityConfig(NegotiateSecurityFilter filter, NegotiateSecurityFilterEntryPoint entryPoint) {
	    this.filter = filter;
	    this.entryPoint = entryPoint;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
	    http
	    	.authorizeRequests()
	    	.anyRequest().authenticated()
	    	.and()
	    	.addFilterBefore(filter, BasicAuthenticationFilter.class)
	    	.exceptionHandling()
	    	.authenticationEntryPoint(entryPoint);
	}
}