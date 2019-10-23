package com.fernando.blog.usersservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.blog.usersservice.dto.UserDTO;
import com.fernando.blog.usersservice.model.LoginRequestModel;
import com.fernando.blog.usersservice.service.UsersService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersService usersService;
    private final Environment environment;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UsersService usersService, Environment environment) {
        this.usersService = usersService;
        this.environment = environment;
        this.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            LoginRequestModel creds = new ObjectMapper().
                    readValue(request.getInputStream(), LoginRequestModel.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getEmail(),
                            creds.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String userName = ((User) authResult.getPrincipal()).getUsername();
        final UserDTO userDetailsByEmail = usersService.getUserDetailsByEmail(userName);
        final String token = Jwts.builder()
                .setSubject(userDetailsByEmail.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(Objects.requireNonNull(environment.getProperty("token.expiration")))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetailsByEmail.getUserId());
    }
}
