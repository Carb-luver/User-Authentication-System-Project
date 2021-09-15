package com.RemoteMode.internshipjava2.jwt;

import com.RemoteMode.internshipjava2.model.JwtUser;
import com.RemoteMode.internshipjava2.service.JwtTokenCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class JwtFilter extends GenericFilterBean {

    JwtProvider jwtProvider;
    JwtTokenCacheService jwtTokenCacheService;
    String url;
    String jwtToken;
    private static final Logger logger = Logger.getLogger(JwtFilter.class.getName());

    public JwtFilter(JwtTokenCacheService jwtTokenCacheService){
        this.jwtTokenCacheService = jwtTokenCacheService;
    }

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorizationHeader = httpServletRequest.getHeader("AuthorizationHeader");
        if(httpServletRequest.getQueryString() == null)
            url = httpServletRequest.getRequestURL().toString();
        else
            url = httpServletRequest.getRequestURL().append(httpServletRequest.getQueryString()).toString();
        if(url.contains("/user")){
            if (checkAuthorizationHeader(authorizationHeader, servletResponse)) {
                jwtToken = authorizationHeader.replace("Bearer ","");
                JwtUser jwtUser = checkIfJwtTokenIsPresent(jwtToken, servletResponse);
            }
        }
    }

    public void logout(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String authorizationHeader = httpServletRequest.getHeader("AuthorizationHeader");
        if(httpServletRequest.getQueryString() == null)
            url = httpServletRequest.getRequestURL().toString();
        else
            url = httpServletRequest.getRequestURL().append(httpServletRequest.getQueryString()).toString();
        if(url.contains("/user") && url.contains("/logout")){
            if (checkAuthorizationHeader(authorizationHeader, servletResponse)) {
                jwtToken = authorizationHeader.replace("Bearer ","");
            }else{
                logger.log(Level.WARNING, "Error found in authorization header.");
            }
        }else {
            logger.log(Level.WARNING, "Error found; user is not valid.");
        }
        jwtTokenCacheService.removeToken(jwtToken);
    }

    public boolean checkAuthorizationHeader(String authorizationHeader, ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (authorizationHeader != null || authorizationHeader.startsWith("Bearer ")) {
            logger.fine("Authorization header is valid.");
            return true;
        }else {
            httpServletResponse.sendError(401);
            logger.log(Level.WARNING, "Error found in authorization header.");
            return false;
        }
    }

    public JwtUser checkIfJwtTokenIsPresent(String jwtToken,ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        try {
            JwtUser jwtUser = jwtProvider.extractJwtUserFromToken(jwtToken);
            if(jwtUser == null){
                httpServletResponse.sendError(401);
                logger.log(Level.INFO, "User not found.");
            }
            return jwtUser;
        } catch (JsonProcessingException e) {
            httpServletResponse.sendError(401);
            logger.log(Level.WARNING, "User lookup unsuccessful");
            return null;
        }
    }

    public JwtUser checkIfJwtTokenIsPresentInCache(String jwtToken, ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if(jwtTokenCacheService.getJwtTokenCache(jwtToken) != null)
            return jwtTokenCacheService.getJwtTokenCache(jwtToken);
        else
            httpServletResponse.sendError(401);
            logger.log(Level.INFO, "Jwt Token lookup unsuccessful.");
            return null;
    }

    public boolean checkIfMethodAllowed(JwtUser userFromToken, String url, ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if(userFromToken.getStringRole() == "ADMIN" && url.contains("/all")){
            return true;
        }else{
            httpServletResponse.sendError(401);
            return false;
        }
    }


}
