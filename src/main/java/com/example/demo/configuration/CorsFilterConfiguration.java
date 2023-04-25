package com.example.demo.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/* SOP(Same-origin policy)
 * A policy that restricts scripts on one origin from accessing data from another origin. 
 * An origin consists of a URI scheme(ex. http, https), domain(ex. localhost) and port number(ex. 80, 8080).
 * 
 * CORS(Cross-Origin Resource Sharing)
 * A security feature implemented by web browsers to allow access to resources from different domains. 
 * CORS enables web pages to interact with resources on other domains while preventing cross-site scripting attacks.
 * 
 * There are three ways to set CORS.
 * 1. Filter
 * 2. @CrossOrigin
 * 3. WebMvcConfigurer
 * 
 * I will set this CORS filter in the Spring Security Configuration file
 * TODO: explain the order of the filters.
 * */

@Configuration
public class CorsFilterConfiguration {

	// This creates a bean for the CORS filter that will be used to add CORS support to the Spring Boot application.
	@Bean
	public CorsFilter corsFilter() {
		// Both UrlBasedCorsConfigurationSource and CorsConfiguration are classes from the Spring Framework's spring-web module.
		// In this project, spring-boot-starter-web includes spring-web as a dependency.
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		// This indicates that the browser should include cookies in cross-origin requests.
		config.setAllowCredentials(true);
		// This sets the allowed origin patterns for the CorsConfiguration object. In this case, it allows requests from any origin.
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		// This sets the allowed headers for the CorsConfiguration object. In this case, it allows any header to be sent in the request.
		config.addAllowedHeader("*");
		// This sets the allowed HTTP methods for the CorsConfiguration object. In this case, it allows any method to be used in the request.
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/api/**", config);
		return new CorsFilter(source);
	}
}