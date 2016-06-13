package com.hz.pet.bexchange;

import com.hz.pet.bexchange.web.BexchangeUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Herman Zamula
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories
@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BexchangeUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/advt/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/advt/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/advt/**").authenticated()
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
