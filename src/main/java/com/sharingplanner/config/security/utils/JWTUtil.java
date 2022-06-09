package com.sharingplanner.config.security.utils;

import com.sharingplanner.common.exception.JWTOmissionException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtil {
    private final Key key;
    private final long tokenValidMilliseconds;
    private final JwtParser parser;

    public JWTUtil(final String textKey, final int minutes) {
        key = Keys.hmacShaKeyFor(textKey.getBytes());
        tokenValidMilliseconds = 1000L * 60 * minutes;
        parser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(String subject, Map<String, Object> data) {
        Claims claims = Jwts.claims().setSubject(subject);
        data.forEach(claims::put);
        Date now = new Date();
        return Jwts.builder().setClaims(claims)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(key)
                .compact();
    }

    public String parseToken(HttpServletRequest request) {
        String tokenPrefix = "Bearer ";
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith(tokenPrefix)) {
            return bearerToken.substring(tokenPrefix.length());
        }

        return "";
    }

    public void validateToken(String token) {
        if (!StringUtils.hasLength(token)) {
            throw new JWTOmissionException("JWT token is not existed");
        }

        try {
            parser.parseClaimsJws(token);
        } catch (SignatureException e) {
            log.error("Invalid JWT signature", e);
            throw e;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token", e);
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token", e);
            throw e;
        }
    }

    public Claims getData(String token) {
        return parser.parseClaimsJws(token).getBody();
    }
}
