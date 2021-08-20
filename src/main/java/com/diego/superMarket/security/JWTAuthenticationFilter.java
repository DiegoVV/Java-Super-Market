package com.diego.superMarket.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.diego.superMarket.data.MyUserDetailsData;
import com.diego.superMarket.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRATION = 600000; // 10 minutes

    public static final String TOKEN_SECRET = "2f937a1c-bd0c-4444-9f26-1df6b564d9e1"; //Ideally this would be hidden in a configuration file, not exposed. But doing it this way for development purposes

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            Employee employee = new ObjectMapper().readValue(request.getInputStream(), Employee.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    employee.getLogin(), employee.getPassword(), new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Failed to authenticate employee", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        MyUserDetailsData myUserDetailsData = (MyUserDetailsData) authResult.getPrincipal();

        String token = JWT.create().withSubject(myUserDetailsData.getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_SECRET));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
