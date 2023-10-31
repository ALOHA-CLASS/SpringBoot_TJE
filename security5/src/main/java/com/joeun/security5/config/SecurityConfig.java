package com.joeun.security5.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((authorize) -> authorize
										.antMatchers("/admin", "/admin/**").hasRole("ADMIN")
										.antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
										.antMatchers("/css/**", "/js/**", "/img/**").permitAll()    // /static/~ 정적자원 인가처리
										.antMatchers("/**").permitAll()
										.anyRequest().authenticated()
								)				
			.formLogin(withDefaults())
			;

		return http.build();
	}

	/**
	 * 
	 * 🟢🟡🔴 인메모리방식으로 인증
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
                               .username("user")
                               .password(passwordEncoder().encode("123456"))
                               .roles("USER")
                               .build();

		UserDetails admin = User.builder()
                               .username("admin")
                               .password(passwordEncoder().encode("123456"))
                               .roles("USER", "ADMIN")
                               .build();
		return new InMemoryUserDetailsManager(user, admin);
	}



	/**
	 * 🍃 AuthenticationManager 빈 등록
	 * @return
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	/**
	 * 🍃 AuthenticationManager 빈 등록
	 * @param authenticationConfiguration
	 * @return
	 * @throws Exception
	 */
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	

}
