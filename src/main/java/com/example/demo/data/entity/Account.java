package com.example.demo.data.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.data.dto.AccountDto.AccountSelectDto;
import com.example.demo.data.entity.common.DefaultEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = UUIDGenerator.class, property = "id")
@Table(name = "account")
@Entity
public class Account extends DefaultEntity {
	
	@Column(length = 191, nullable = false, unique = true)
	private String email;
	
	@Column(length = 191, nullable = false)
	private String password;
	
	@Column(length = 32, nullable = false)
	private String firstName;
	
	@Column(length = 32, nullable = false)
	private String lastName;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<AccountRole> accountRole = new ArrayList<>();
	
	@Builder
	public Account(String id, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, 
				   String email, String password, String firstName, String lastName, List<AccountRole> accountRole) {
		super(id, createdAt, createdBy, updatedAt, updatedBy);
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountRole = accountRole;
	}
	
	public AccountSelectDto toSelectDto() {
		return AccountSelectDto.builder()
							   .id(super.getId())
				  	     	   .email(email)
				  	     	   .firstName(firstName)
				  	     	   .lastName(lastName)
				  	     	   .build();
	}	
	
	
}