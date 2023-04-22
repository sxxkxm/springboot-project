package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.data.dto.AccountDto.AccountCreateDto;
import com.example.demo.data.entity.Account;
import com.example.demo.data.repository.AccountRepository;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AccountRepository accountRepository;
	
	@Autowired
	public AccountServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, AccountRepository accountRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.accountRepository = accountRepository;
	}
	
	@Override
	public AccountCreateDto saveAccount(AccountCreateDto accountCreateDto) {
		Account account = accountCreateDto.toEntity();
		String rawPassword = account.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		account.setPassword(encPassword);
		account = accountRepository.save(account);
		return account.toCreateDto();
	}
	
}