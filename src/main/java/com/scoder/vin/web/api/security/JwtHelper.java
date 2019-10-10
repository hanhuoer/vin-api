package com.scoder.vin.web.api.security;

import com.scoder.vin.web.api.util.DateTimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author H
 */
public class JwtHelper {

    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * @param claims      claims
     * @param expiredDate expired date time {@code: java.util.Date}
     * @return String
     */
    private static String tokenGenerate(Map<String, Object> claims, Date expiredDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expiredDate)
                .signWith(key)
                .compact();
    }

    /**
     * @param userId         user id {@code: java.lang.Long}
     * @param expiredSeconds expired seconds {@code: java.lang.Integer}
     * @return String
     */
    public static String tokenGenerate(Long userId, Integer expiredSeconds) {
        Map<String, Object> claims = new HashMap<String, Object>(4) {{
            put("userId", userId);
        }};
        return tokenGenerate(claims, DateTimeUtils.addSeconds(new Date(), expiredSeconds));
    }

    /**
     * token parse
     *
     * @param jws jwt string
     * @return Jws<Claims>
     */
    public static Jws<Claims> tokenParse(String jws) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jws);
    }

}
