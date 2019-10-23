package com.fernando.blog.zuulapigatway.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http .authorizeRequests()
                .antMatchers(HttpMethod.POST, environment .getProperty("api.h2console.url.path")) .permitAll()
                .antMatchers(HttpMethod.POST, environment .getProperty("api.registration.url.path")) .permitAll()
                .antMatchers(HttpMethod.POST, environment .getProperty("api.login.url.path")) .permitAll()
        .anyRequest() .authenticated()
                .and()
                .addFilter(
                        new AuthorizationFilter(
                                authenticationManager(),
                                this.environment))
     ;
        http.sessionManagement()
                .sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS);
        super.configure(http);
    }
}
