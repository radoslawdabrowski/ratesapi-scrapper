package com.hsbc.ratesapiscrapper.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .httpBasic().disable()
            .authorizeRequests()
            .mvcMatchers(
                "/h2-console"
            )
            .permitAll()
            .mvcMatchers(
                "/api/*/error/**"
            )
            .permitAll()
            .mvcMatchers(
                "/api/*/rates", "/api/*/rates/**"
            )
            .permitAll()
            .anyRequest()
            .authenticated();
    }
}
