package com.example.demo.service;

import com.example.demo.data.dto.AccountDto.AccountSelectDto;
import com.example.demo.data.dto.AccountDto.AccountSignupDto;

public interface AccountService {
	AccountSelectDto signupAccount(AccountSignupDto accountSignupDto);
}