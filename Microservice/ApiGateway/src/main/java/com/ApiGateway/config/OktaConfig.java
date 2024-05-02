package com.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity    //this is not normal spring web security.spring cloud  Gateway support the flux security
public class OktaConfig {

	@SuppressWarnings("removal")
	@Bean
	SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverSecurity) {
		 
		 serverSecurity
		 	.authorizeExchange()
		 	.anyExchange()
		 	.authenticated()
		 	.and()
		 	.oauth2Client()
		 	.and()
		 	.oauth2ResourceServer()
		 	.jwt();
		 
		 return serverSecurity.build();
	 }

}
