package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/account")
public class AccountRestController {
	
	
	//PreAuthorize("permitAll()")
	@PostMapping("/signup")
	public void signupAccount() {
	}
	
	
}