package com.RemoteMode.internship2correction.jwt;

import com.RemoteMode.internship2correction.model.JwtUser;
import com.RemoteMode.internship2correction.service.JwtTokenCacheService;
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
import java.util.Map;

@Component
public class JwtFilter extends GenericFilterBean {

    JwtProvider jwtProvider;
    JwtTokenCacheService jwtTokenCacheService;
    Map<String, JwtUser> jwtTokenCache;
    JwtUser jwtUser;

    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String url = jwtProvider.getUrl(httpServletRequest);
        String AuthorizationHeader = jwtProvider.getAuthorizationHeader(httpServletResponse);
        if(url.contains("/user") && checkAuthorizationHeader(httpServletResponse)){
            String jwtToken = jwtProvider.getJwtToken(httpServletResponse);
            jwtTokenCache = jwtTokenCacheService.getJwtTokenCache();
            if (jwtTokenCache.containsKey(jwtToken)){
                jwtUser = jwtTokenCache.get(jwtToken);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    public boolean checkAuthorizationHeader(HttpServletResponse httpServletResponse) throws IOException {
        if (!jwtProvider.getAuthorizationHeader(httpServletResponse).startsWith("Bearer ") || jwtProvider.getAuthorizationHeader(httpServletResponse) == null) {
            httpServletResponse.sendError(401);
            return false;
        }else{
            return (jwtProvider.getAuthorizationHeader(httpServletResponse).startsWith("Bearer ") && jwtProvider.getAuthorizationHeader(httpServletResponse) != null);
        }
    }

    public JwtUser checkIfJwtTokenIsPresent(HttpServletResponse httpServletResponse) throws IOException {
        try {
            jwtUser = jwtProvider.checkIfJwtTokenIsPresent(httpServletResponse);
        }catch (JsonProcessingException e){
            httpServletResponse.sendError(401);
        }
        if (jwtUser == null){
            httpServletResponse.sendError(401);
        }
        return jwtUser;
    }

    public boolean checkIsMethodAllowed(HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws IOException {
        JwtUser userFromToken = checkIfJwtTokenIsPresent(httpResponse);
        String url = jwtProvider.getUrl(httpRequest);
        if(userFromToken.getStringRole() == "ADMIN" && jwtProvider.getUrl(httpRequest).contains("/all")){
            return true;
        }else {
            httpResponse.sendError(403);
            return false;
        }
    }

}
