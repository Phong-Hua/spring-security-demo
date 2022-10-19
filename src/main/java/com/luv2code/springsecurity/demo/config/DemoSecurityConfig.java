package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

//DemoSecurityConfig need to extend WebSecurityConfigurerAdapter
@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// add our users for in memory authentication

		UserBuilder users = User.withDefaultPasswordEncoder();

		// specify we use in memory authentication
		auth.inMemoryAuthentication().withUser(users.username("john").password("test123").roles("EMPLOYEE"))
				.withUser(users.username("mary").password("test123").roles("MANAGER"))
				.withUser(users.username("susan").password("test123").roles("ADMIN"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // this line: restrict access based on the HttpServlet Request
				.anyRequest().authenticated() // this line: any request to the app must be authenticated
				.and()
					.formLogin() // this line: we customize the login process
					.loginPage("/showMyLoginPage") // this line: our login form is at "/showMyLoginPage". We need a
													// controller for this mapping.
					.loginProcessingUrl("/authenticateTheUser") // this line: login form should POST data to this URL for
																// processing. No controller request mapping for this url.
																// Spring Security Filters will do this.
					.permitAll() // this line: allow everyone to see login page. No need to be logged in.
				.and()
					.logout()	// adds logout support
					.permitAll()
		;
	}
}
