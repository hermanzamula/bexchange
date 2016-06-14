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

        http
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/advts/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/advts/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/advts/**").hasRole("USER")
                .antMatchers(HttpMethod.PATCH, "/advts/**").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/advts/**").permitAll()
                .antMatchers(HttpMethod.HEAD, "/advts/**").permitAll()

                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.HEAD, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/users").anonymous()
                .and()
                .csrf().disable()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
