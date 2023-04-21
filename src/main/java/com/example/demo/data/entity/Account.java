package com.example.demo.data.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
	
	@Builder
	public Account(String id, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, 
				   String email, String password, String firstName, String lastName) {
		super(id, createdAt, createdBy, updatedAt, updatedBy);
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
}