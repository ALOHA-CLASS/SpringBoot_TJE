package com.joeun.springsecurity.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.joeun.springsecurity.security.CustomUserDetailsService;
import com.joeun.springsecurity.security.LoginSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration                  // 설정 클래스
@EnableWebSecurity              // 해당 클래스를 스프링 시큐리티 설정 빈으로 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;        // 비밀번호 암호화 객체 

    @Autowired
    private DataSource dataSource;          // application.properites 에 정의한 데이터 소스를 가져오는 객체
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 스프링 시큐리티 설정
        log.info("스프링 시큐리티 설정...");

        // 인증 & 인가
        // ✅ 인증 (authentication)
        // : 등록된 사용자인지 확인하여 입증

        // ✅ 인가 (authorization)
        // : 사용자의 권한을 확인하여 권한에 따라 자원의 사용범위를 구분하여 허락하는 것

        // 인가 처리
        http.authorizeRequests()                   // 인가 설정
            //  antMatchers("자원 경로")            - 인가에 대한 URL 경로를 설정
            //  permitAll()                        - 모든 사용자 허용
            //  hasAnyRole()                       - 여러 권한에 대한 허용 
            //  hasRole()                          - 단일 권한에 대한 허용
            .antMatchers("/").permitAll()
            .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/admin/**").hasRole("ADMIN")
            ;

        // 로그인 설정
        http.formLogin()
            .defaultSuccessUrl("/")         // 로그인 성공 시, URL : "/"(기본값)
            // .loginPage("/login")                    // 커스텀 로그인 페이지 지정 (default:/login)
            // .loginProcessingUrl("/loginPro")// 커스럼 로그인 요청 처리 경로 지정 (default:/login)
            // .usernameParameter("id")        // 아이디 요청 파라미터 이름 설정  (default:username)
            // .passwordParameter("pw")        // 비밀번호 요청 파라미터 이름 설정 (default:password)
            // .successHandler( authenticationSuccessHandler() ) // 로그인 성공 처리 빈을 지정
            .permitAll()                                      // 로그인 폼은 모든 사용자에게 허용
            ;

        // 로그아웃 설정
        http.logout()
            // .logoutSuccessUrl("/login")      // 로그아웃 성공 시, URL : "/login?logout" (기본값)
            // .logoutUrl("/logout")                   // 로그아웃 요청 처리 경로 지정 (default:logout)
            .permitAll()
            ;

        // 자동로그인 설정
        // http.rememberMe()
        //     .key("joeun")
        //     // DataSource 가 등록된 PersistentRepository 토큰정보 객체 
        //     .tokenRepository( tokenRepository() )
        //     .tokenValiditySeconds( 60 * 60 * 24 * 7 )                    // 토큰 유효기간 : 7일
        //     ;


        // CSRF 방지 기능 비활성화 설정
        // http.csrf().disable();
    }

    // 인증 관리 메소드
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // AuthenticationManagerBuilder : 인증 관리 객체
        // 인증 방식 : 인메모리 방식
        // auth.inMemoryAuthentication()               
        //     // .withUser("아이디").password("비밀번호").roles("권한")
        //     // passwordEncoder.encode("비밀번호")     :   비밀번호 암호화
        //     // BCryptPasswordEncoder 사용
        //     .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
        //     .and()
        //     .withUser("admin").password(passwordEncoder.encode("123456")).roles("ADMIN")
        //     ;
            // NoOpPasswordEncoder 사용
            // .withUser("user").password("123456").roles("USER")
            // .and()
            // .withUser("admin").password("123456").roles("ADMIN")
            // ;
        
        // 인증 방식 : jdbc 인증
        // String sql1 = " SELECT user_id as username, user_pw as password, enabled "
        //             + " FROM user "
        //             + " WHERE user_id = ? ";

        // String sql2 = " SELECT user_id as username, auth " 
        //             + " FROM user_auth "
        //             + " WHERE user_id = ? ";

        // auth.jdbcAuthentication()
        //     // 데이터 소스 등록
        //     .dataSource( dataSource )
        //     // 인증 쿼리    (아이디/비밀번호/활성여부)
        //     .usersByUsernameQuery(sql1)
        //     // 인가 쿼리    (아이디/권한)
        //     .authoritiesByUsernameQuery(sql2)
        //     // 비밀번호 암호화 방식 지정 - BCryptPasswordEncoder 또는 NoOpPasswordEncoder
        //     .passwordEncoder( passwordEncoder );


        // 인증 방식 : 사용자 정의 인증 (UserDetails)
        auth.userDetailsService( CustomUserDetailsService() )
            // 비밀번호 암호화 방식 지정 - BCryptPasswordEncoder 또는 NoOpPasswordEncoder
            .passwordEncoder( passwordEncoder );



            
    }

    // PersistentRepository 토큰정보 객체 - 빈 등록
    public PersistentTokenRepository tokenRepository() {
        // JdbcTokenRepositoryImpl : 토큰 저장 데이터 베이스를 등록하는 객체
        JdbcTokenRepositoryImpl repositoryImpl = new JdbcTokenRepositoryImpl(); 
        repositoryImpl.setDataSource(dataSource);   // 토큰 저장소를 사용하는 데이터 소스 지정
        return repositoryImpl;
    }

        
    // 인증 성공 처리 클래스 - 빈 등록
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginSuccessHandler();
    }


    @Bean
    public UserDetailsService CustomUserDetailsService() {
        return new CustomUserDetailsService();
    }


    // 인증 관리자 클래스 - 빈 등록
    // @Bean
    // public AuthenticationManager authenticationManager() throws Exception {
    //     return super.authenticationManagerBean();
    // }




    
    
}
