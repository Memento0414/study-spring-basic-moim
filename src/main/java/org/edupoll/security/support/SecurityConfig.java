package org.edupoll.security.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


		http.csrf(t-> t.disable());
		http.authorizeHttpRequests(t -> t
									.requestMatchers("/","/user/**", "/moim/list", "/search/**").permitAll()
									.anyRequest().authenticated()	
				);

		http.formLogin(t ->t.usernameParameter("loginId")
							.passwordParameter("loginPass")
							.loginPage("/user/login").permitAll().defaultSuccessUrl("/"));
		
	 return http.build();
 }
	
}
