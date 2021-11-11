package com.RemoteMode.internship2correction.service;

import com.RemoteMode.internship2correction.model.JwtUser;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtTokenCacheService {

    Map<String, JwtUser> jwtTokenCache;


    public void addToken(String jwtToken, JwtUser jwtUser){
        jwtTokenCache.put(jwtToken, jwtUser);
    }

    public void removeToken(JwtUser jwtUser){
        jwtTokenCache.remove(jwtUser);
    }

    public JwtUser retreiveJwtUserFromCacheWithToken(String jwtToken, HttpServletResponse httpServletResponse) throws IOException {
        if (!jwtTokenCache.containsKey(jwtToken))
            httpServletResponse.sendError(401);
        return jwtTokenCache.get(jwtToken);
    }

    public Map<String, JwtUser> getJwtTokenCache() {
        return jwtTokenCache;
    }
}
