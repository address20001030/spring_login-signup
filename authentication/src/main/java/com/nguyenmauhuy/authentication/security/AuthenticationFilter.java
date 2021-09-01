package com.nguyenmauhuy.authentication.security;

import com.nguyenmauhuy.authentication.exception.BusinessCodeResponse;
import com.nguyenmauhuy.authentication.exception.BusinessException;
import com.nguyenmauhuy.authentication.model.response.BaseResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends GenericFilterBean {
    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
    private final TokenAuthenticator authenticator;
    private ObjectMapper mapper;


    public AuthenticationFilter(TokenAuthenticator authenticator, ObjectMapper mapper) {
        this.authenticator = authenticator;
        this.mapper = mapper;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Authentication userAuth;
        String token = ((HttpServletRequest) req).getHeader(AUTH_HEADER_NAME);

        try {
            userAuth = this.authenticator.getAuthentication(token);
        } catch (InvalidJwtException e) {
            HttpServletResponse resp = (HttpServletResponse) res;
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            BusinessException err;
            if (e.getMessage().contains("no longer valid")) {
                err = new BusinessException(BusinessCodeResponse.JWT_EXPIRED);
            } else {
                err = new BusinessException(BusinessCodeResponse.JWT_INVALID);
            }
            this.mapper.writeValue(resp.getOutputStream(), BaseResponse.ofFail(err));
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(userAuth);
        chain.doFilter(req, res);
    }


}
