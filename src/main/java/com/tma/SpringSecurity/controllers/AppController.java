package com.tma.SpringSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	
	@GetMapping("/")
	public String showHomePage() {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginView() {
		
		return "login";
	}
	
	@GetMapping("/login_error")
	public String showLoginError() {
		
		return "login_error";
	}
	
	@GetMapping("/userinfo")
	public String showUserInfo() {
		
		return "userinfo";
	}
	
	@GetMapping("/admin")
	public String showAdminSite() {
		
		return "admin";
	}
	
	@GetMapping("/403")
	public String show403ErrorPage() {
		
		return "403";
	}
}
