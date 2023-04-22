package com.example.demo.service;

import com.example.demo.data.dto.AccountDto.AccountCreateDto;

public interface AccountService {
	AccountCreateDto saveAccount(AccountCreateDto accountCreateDto);
}