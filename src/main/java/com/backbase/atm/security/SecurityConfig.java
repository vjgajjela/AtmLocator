package com.backbase.atm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.backbase.atm.IConstants;

/**
 * This class defines basic REST authentications specific to API path
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Basic Authentication entry point
	 */
	@Autowired
	private AtmBasicAuthenticationEntryPoint authEntryPoint;

	/**
	 * @param auth
	 * @throws Exception
	 *
	 *             This method defines roles to be authenticated against.
	 */
	@Autowired
	public void configureGlobalSecurity(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(IConstants.USER).password(IConstants.PWD).roles(IConstants.ROLE);
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/city/**").hasRole(IConstants.ROLE)
				.antMatchers("/search/**").hasRole(IConstants.ROLE).antMatchers("/postcode/**").hasRole(IConstants.ROLE)
				.antMatchers("/AtmLocator/**").hasRole(IConstants.ROLE).anyRequest().authenticated().and().httpBasic()
				.realmName(IConstants.REALM).authenticationEntryPoint(authEntryPoint);
	}

}
