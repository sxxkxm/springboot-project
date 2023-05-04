package com.example.demo.service.impl;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.RoleNames;
import com.example.demo.data.dto.AccountDto.AccountSelectDto;
import com.example.demo.data.dto.AccountDto.AccountSignupDto;
import com.example.demo.data.entity.Account;
import com.example.demo.data.entity.AccountRole;
import com.example.demo.data.entity.Role;
import com.example.demo.data.repository.AccountRepository;
import com.example.demo.data.repository.AccountRoleRepository;
import com.example.demo.data.repository.RoleRepository;
import com.example.demo.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	/*
	 * TODO: bCryptPasswordEncoder: 
	 */
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AccountRepository accountRepository;
	private final RoleRepository roleRepository;
	private final AccountRoleRepository accountRoleRepository;
	
	@Autowired
	public AccountServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, AccountRepository accountRepository, RoleRepository roleRepository, AccountRoleRepository accountRoleRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.accountRoleRepository = accountRoleRepository;
	}
	
	@Override
	public AccountSelectDto signupAccount(AccountSignupDto accountSignupDto) {
		Account account = accountSignupDto.toEntity();
		// encode password
		String rawPassword = account.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		account.setPassword(encPassword);
		// save account
		account = accountRepository.save(account);
		// save accountRole as user
		Role roleUser = roleRepository.findByName(RoleNames.USER).orElseThrow(new Supplier<RuntimeException>() {
			@Override
			public RuntimeException get() {
				return new RuntimeException("Role " + RoleNames.USER + " not found");
			}
		});
		AccountRole accountRole = AccountRole.builder().account(account).role(roleUser).build();
		accountRoleRepository.save(accountRole);
		return account.toSelectDto();
	}
	
}