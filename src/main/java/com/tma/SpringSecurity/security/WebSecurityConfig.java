package com.tma.SpringSecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tma.SpringSecurity.handlers.OnAuthenFailure;
import com.tma.SpringSecurity.handlers.OnAuthenSuccess;
import com.tma.SpringSecurity.handlers.OnLogoutSuccess;
import com.tma.SpringSecurity.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authen = new DaoAuthenticationProvider();
		
		authen.setUserDetailsService(userDetailsService());
		authen.setPasswordEncoder(passwordencoder());
		
		return authen;
	}
		

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/assets/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/vendor/**", 
						"/index", "/login", "/logout", "/403")
				.permitAll()
				.antMatchers("/userinfo").hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/admin").hasAnyAuthority("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin().loginPage("/login").permitAll()
				.usernameParameter("username")
				.passwordParameter("password")
				.loginProcessingUrl("/dologin")
				.failureHandler(new OnAuthenFailure())
				.successHandler(new OnAuthenSuccess())
				.and().logout().permitAll()
				.logoutUrl("/dologout")
				.logoutSuccessHandler(new OnLogoutSuccess())
				.and().exceptionHandling().accessDeniedPage("/403");
			
	}
	
	
}
