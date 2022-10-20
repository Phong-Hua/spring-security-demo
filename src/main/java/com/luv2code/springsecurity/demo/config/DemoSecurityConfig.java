package com.luv2code.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private DataSource securityDataSource;	// Inject our data source
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication
		auth.jdbcAuthentication().dataSource(securityDataSource);	// tell Spring Security to use JDBC authentication 
										// with our data source, no more hard-coding
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() // this line: restrict access based on the HttpServlet Request
				// Delete anyRequest().authenticated() because this line means anyone who authenticated can access any url
				// .anyRequest().authenticated() // this line: any request to the app must be authenticated
				.antMatchers("/").hasRole("EMPLOYEE")	// any one who has role EMPLOYEE can access the root
				.antMatchers("/leaders/**").hasRole("MANAGER")	// anyone with MANAGER role can access /leaders/**, ** mean sub-directories
				.antMatchers("/systems/**").hasRole("ADMIN")	// anyone with ADMIN role can access /systems/**
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
				.and()
					.exceptionHandling().accessDeniedPage("/access-denied")
		;
	}
}
