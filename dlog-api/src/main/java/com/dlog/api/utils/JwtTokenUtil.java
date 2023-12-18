package com.dlog.api.utils;


import java.util.Date;

import org.springframework.context.annotation.Configuration;

import com.dlog.api.dto.UserInfoDto;
import com.dlog.api.model.user.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class JwtTokenUtil {
	
	private static String secretKey = "dlog-secretkey"; // 서명에 사용할 secretKey
	
	// JWT Token 발급
    public static String createToken(User user, String key, long expireTimeMs) {

        Claims claims = Jwts.claims();
        claims.put("userId", user.getUserId());
        claims.put("email", user.getEmail());
        claims.put("rowNumber", user.getRowId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static String getLoginId(String token) {
        return extractClaims(token).get("userId").toString();
    }

    public static boolean isExpired(String token) {
        Date expiredDate = extractClaims(token).getExpiration();
        // Token의 만료 날짜가 지금보다 이전인지 check
        return expiredDate.before(new Date());
    }
    
    public static Claims extractClaims(String token) {
    	Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    	return claim;
    }

    public static UserInfoDto getUserInfo(String token) {
    	Claims claim = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    	UserInfoDto dto = new UserInfoDto();
    	dto.setUserId(claim.get("userId").toString());
    	dto.setEmail(claim.get("email").toString());
    	dto.setRowId(Long.parseLong(claim.get("rowNumber").toString()));
    	return dto;
    }
   
    
}