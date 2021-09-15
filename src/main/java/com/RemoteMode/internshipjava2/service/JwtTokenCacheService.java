package com.RemoteMode.internshipjava2.service;

import com.RemoteMode.internshipjava2.model.JwtUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.RemoteMode.internshipjava2.jwt.*;

import javax.servlet.ServletResponse;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JwtTokenCacheService {

    Map<String, JwtUser> jwtTokenCache;
    JwtProvider jwtProvider;
    private static final Logger logger = Logger.getLogger(JwtTokenCacheService.class.getName());


    public String addToken(JwtUser jwtUser) throws JsonProcessingException {
        String jwtToken = jwtProvider.generateToken(jwtUser);
        jwtTokenCache.put(jwtToken, jwtUser);
        return jwtToken;
    }

    public void removeToken(JwtUser jwtUser){

    }

    public JwtUser getJwtTokenCache(String jwtToken) {
        if(jwtTokenCache.containsKey(jwtToken))
            return jwtTokenCache.get(jwtToken);
        else
            logger.log(Level.INFO, "Jwt Token lookup unsuccessful.");
        return null;
    }
}
