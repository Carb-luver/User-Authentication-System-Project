package com.RemoteMode.internship2correction.jwt;

import com.RemoteMode.internship2correction.exception.BadRequestException;
import com.RemoteMode.internship2correction.model.JwtUser;
import com.RemoteMode.internship2correction.service.JwtTokenCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtProvider {

    ObjectMapper objectMapper;
    JwtFilter jwtFilter;
    @Value("${jwt.secret}") String secretKey;
    JwtTokenCacheService jwtTokenCacheService;
    Map<String, JwtUser> jwtTokenCache;

    public String generateToken(JwtUser jwtUser) throws JsonProcessingException {
        try {
            String jwtSubject = objectMapper.writeValueAsString(jwtUser);
            String jwtToken = Jwts.builder().setSubject(jwtSubject).signWith(SignatureAlgorithm.HS512, secretKey).compact();
            jwtTokenCacheService.addToken(jwtToken, jwtUser);
            return jwtToken;
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Unable to generate token", e);
        }
    }

    public void removeJwtUserFromCache(HttpServletResponse httpServletResponse){
        jwtTokenCacheService.getJwtTokenCache().remove(getJwtToken(httpServletResponse));
    }

    public JwtUser checkIfJwtTokenIsPresent(HttpServletResponse httpServletResponse) throws IOException {
        String jwtToken = getJwtToken(httpServletResponse);
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(jwtToken).getBody();
        String jwtSubject = claims.getSubject();
        return objectMapper.readValue(jwtSubject, JwtUser.class);
    }

    public String getUrl(HttpServletRequest httpServletRequest){
        return httpServletRequest.getRequestURL().toString();
    }

    public String getAuthorizationHeader(HttpServletResponse httpServletResponse){
        return httpServletResponse.getHeader("Authorization");
    }

    public String getJwtToken(HttpServletResponse httpServletResponse){
        return httpServletResponse.getHeader("Authorization").replace("Bearer ","");
    }

}
