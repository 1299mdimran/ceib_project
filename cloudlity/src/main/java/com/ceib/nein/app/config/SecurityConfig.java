package com.ceib.nein.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ceib.nein.app.services.UserServiceInfo;
import com.ceib.nein.app.services.impl.UserServiceImpl;
import com.ceib.nein.app.web.LoggingAccessDeniedHandler;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
    private UserServiceInfo userService;
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;
	@Autowired
    CustomSuccessHandler customSuccessHandler;
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
				.antMatchers("/user/**").hasRole("DEO")
				.antMatchers("/dba/**").hasRole("DBA")
				//.antMatchers("/signup/**").hasRole("DBA")
				.anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.successHandler(customSuccessHandler)
		        .usernameParameter("userName")
		        .passwordParameter("password")
		        .permitAll()
				.and()
			.logout()
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
		
	
	
		
		/*auth.inMemoryAuthentication().withUser("user").password("password").roles("USER").and().withUser("manager")
				.password("password").roles("DBA");*/
	}
	
	@Bean
	 public PasswordEncoder passwordEncoder(){
	  return new BCryptPasswordEncoder();
	 }

}