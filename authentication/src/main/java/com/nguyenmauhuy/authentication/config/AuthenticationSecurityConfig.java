package com.nguyenmauhuy.authentication.config;

import com.nguyenmauhuy.authentication.security.AuthenticationFilter;
import com.nguyenmauhuy.authentication.security.TokenAuthenticator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class AuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticator tokenAuthenticator;
    @Autowired
    private ObjectMapper objectMapper;

    public AuthenticationSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .and()
                .anonymous()
                .and()
                .servletApi()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/favicon.ico")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/user/auth")
                .permitAll()
                .and()
                .headers()
                .cacheControl();

        http.authorizeRequests().anyRequest().hasRole("USER");

        http.addFilterBefore(new AuthenticationFilter(tokenAuthenticator, objectMapper), UsernamePasswordAuthenticationFilter.class);
    }

}
