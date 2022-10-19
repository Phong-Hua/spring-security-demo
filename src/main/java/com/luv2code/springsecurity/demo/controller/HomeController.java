package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}

	// add request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaders() {
		return "leaders";
	}

	// add request mapping for /systems
	@GetMapping("/systems")
	public String showSystems() {
		return "systems";
	}
	
	// add request mapping for /vip
		@GetMapping("/vip")
		public String showVip() {
			return "vip";
		}
}
