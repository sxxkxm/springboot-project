package com.example.demo.data.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.data.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AccountDto {
	
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AccountCreateDto {
		
		@NotNull
		@NotEmpty
		@Email
		@Size(max=191)
		private String email;
		
		@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,}")
		@NotNull
		@NotEmpty
		@Size(min=8, max=191)
		private String password;
		
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String firstName;
		
		@NotNull
		@NotEmpty
		@Size(max=32)
		private String lastName;
		
		public Account toEntity() {
			return Account.builder()
							   	.email(email)
							   	.password(password)
							   	.firstName(firstName)
							   	.lastName(lastName)
							   	.build();
		}
		
	}
	
}