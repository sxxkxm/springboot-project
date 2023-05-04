package com.example.demo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.data.entity.Account;
import com.example.demo.data.entity.AccountRole;


public interface AccountRoleRepository extends JpaRepository<AccountRole, String> {
	
}