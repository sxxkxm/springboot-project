package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfiguration {
	
	private final ObjectMapper objectMapper;
	private final CorsFilterConfiguration corsFilterConfiguration;
	
	@Autowired
	public SecurityConfiguration(ObjectMapper objectMapper, CorsFilterConfiguration corsFilterConfiguration) {
		this.objectMapper = objectMapper;
		this.corsFilterConfiguration = corsFilterConfiguration;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    /**
	 *  Spring Security Authorization config.
	 *  
	 *  @param HttpSecurity http
	 *  @throws Exception
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable()
				   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				   .formLogin().disable()
				   .httpBasic().disable()
				   .authorizeRequests(authroize -> authroize.anyRequest().permitAll())
				   .build();
	}
	
	public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
		
	    /**
		 *  @param HttpSecurity http
		 *  @throws Exception
		 */
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.addFilter(corsFilterConfiguration.corsFilter());
		}
		
	}	

}