package com.joeun.springsecurity5.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.joeun.springsecurity5.security.CustomAuthenticationManager;
import com.joeun.springsecurity5.security.CustomUserDetailService;

@EnableWebSecurity
public class SecurityConfig  {


    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
										.antMatchers("/**").permitAll()
										.anyRequest().authenticated()
								)				
			.httpBasic(withDefaults())
			.formLogin(withDefaults())
			.authenticationManager(authenticationManager());
			;

		return http.build();
	}

	/**
	 * 인메모리방식으로 인증
	 * @return
	 */
	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails user = User.builder()
    //                            .username("user")
    //                            .password(passwordEncoder().encode("123456"))
    //                            .roles("USER")
    //                            .build();
	// 	return new InMemoryUserDetailsManager(user);
	// }


	/**
	 * 
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService(CustomUserDetailService customUserDetailService) {
		return customUserDetailService;
	}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	
    @Bean
    public AuthenticationManager authenticationManager() {
        return new CustomAuthenticationManager();
    }


}
