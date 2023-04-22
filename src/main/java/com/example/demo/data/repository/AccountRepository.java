package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.Account;


public interface AccountRepository extends JpaRepository<Account, String> {
	
}