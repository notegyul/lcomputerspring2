package com.lcomputerspring2.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.lcomputerspring2.example.service.UserService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig{

	@Autowired
	private UserService userService;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		
		http.csrf().disable();
		
		//	인증과 권한
		http
		.authorizeHttpRequests()
			.requestMatchers("/user/**").authenticated()
			.requestMatchers("/admin/**").hasAnyRole("ROLE")
			.anyRequest().permitAll();
			
		//	폼 로그인 설정
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/loginPro")
			.defaultSuccessUrl("/", true)
			.permitAll();
			
		//	로그아웃 설정
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "remember-me");
			
			
		//	remember me 설정
		http.rememberMe()
			.key("muWeb")
			.rememberMeParameter("remember-me")
			.tokenValiditySeconds(86400);//1day
			
		//	exceptionHandling
		http.exceptionHandling()
			.accessDeniedPage("/denied");
			
		//	session 관리
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.invalidSessionUrl("/login");
			
		
		return http.build();

		
	}
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl(); 
		db.setDataSource(dataSource);
		return db;
	}
	
	//security 기본설정
			
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
	
	
	
	
	
}
