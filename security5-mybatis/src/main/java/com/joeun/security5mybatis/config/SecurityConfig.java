package com.joeun.security5mybatis.config;


import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.joeun.security5mybatis.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	private CustomUserDetailService customUserDetailService;


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
			.userDetailsService(customUserDetailService)
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



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	

}
