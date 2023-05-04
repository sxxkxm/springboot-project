package com.example.demo.data.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.data.entity.Account;
import com.example.demo.data.entity.Role;

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
	public static class AccountSignupDto {
		
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
	
	@Builder
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class AccountSelectDto {
		
		private String id;
		private String email;
		private String firstName;
		private String lastName;
		private Set<Role> roles;
		
	}	
	
}