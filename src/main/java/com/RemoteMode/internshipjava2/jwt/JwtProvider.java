package com.RemoteMode.internshipjava2.jwt;

import com.RemoteMode.internshipjava2.exception.BadRequestException;
import com.RemoteMode.internshipjava2.model.JwtUser;
import com.RemoteMode.internshipjava2.model.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.logging.Logger;

@Component

public class JwtProvider {

    ObjectMapper objectMapper = new ObjectMapper();
    JwtBuilder builder = Jwts.builder();
    String jsonSubject;
    OAuth2ResourceServerProperties.Jwt jwt;
    private static final Logger logger = Logger.getLogger(JwtProvider.class.getName());


    @Value("${jwt.secret}")
    private String jwtsecret;

    public String generateToken(JwtUser jwtUser) throws JsonProcessingException, BadRequestException {
        try {
            jsonSubject = objectMapper.writeValueAsString(jwtUser);
            logger.fine("Generating jwtToken....");
        } catch (JsonProcessingException e) {
            throw new BadRequestException("Exception when generating jwtToken", e);
        }
        return builder.setSubject(jsonSubject).signWith(SignatureAlgorithm.HS512,jwtsecret).compact();
    }

    public JwtUser extractJwtUserFromToken(String jwtToken) throws JsonProcessingException {
        jsonSubject = extractClaim(jwtToken, Claims::getSubject);
        logger.fine("Retrieving user from database");
        return objectMapper.readValue(jsonSubject, JwtUser.class);
    }

    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(jwtsecret).parseClaimsJws(jwtToken).getBody();
    }
}
