package com.fernando.blog.usersservice.security;

import com.fernando.blog.usersservice.service.UsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@Configuration
@EnableWebSecurity
public class WebSecurity
        extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final UsersService usersService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(Environment environment, UsersService usersService,
                       BCryptPasswordEncoder bCryptPasswordEncoder
                       ) {
        this.environment = environment;
        this.usersService = usersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(
                this.authenticationManager(),
                usersService,
                environment);
        authenticationFilter.setFilterProcessesUrl(Objects.requireNonNull(environment.getProperty("login.url.path")));
        http.authorizeRequests()
                .antMatchers("/users/**")
                .hasIpAddress(environment.getProperty("gateway.ip"))
        .and()
        .addFilter(authenticationFilter );
        http.headers().frameOptions().disable();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }


}
