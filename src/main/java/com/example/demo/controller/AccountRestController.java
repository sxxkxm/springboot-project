package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.dto.AccountDto.AccountSelectDto;
import com.example.demo.data.dto.AccountDto.AccountSignupDto;
import com.example.demo.service.AccountService;


@RestController
@RequestMapping("/api/v1/account")
public class AccountRestController {
	
	private AccountService accountService;
	
	@Autowired
	public AccountRestController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PreAuthorize("permitAll()")
	@PostMapping("/signup")
	public ResponseEntity<AccountSelectDto> signupAccount(@Valid @RequestBody AccountSignupDto accountSignupDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(accountService.signupAccount(accountSignupDto));
	}
	
}