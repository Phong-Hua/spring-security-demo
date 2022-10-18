package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc	// This provide similar support to <mvc:annotation-driven>, allows us to 
				// access to @Controller, @RequestMapping, etc...
@ComponentScan(basePackages = "com.luv2code.springsecurity.demo")
public class DemoAppConfig {

	// we need to define a bean for ViewResolver
	// With the view resolver, spring knows where to look for our jsp files
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
}
