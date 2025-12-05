package com.sorcerer.jwt_service.service;

import com.sorcerer.jwt_service.dto.JwtResponseDto;
import com.sorcerer.jwt_service.entity.ClientDetails;
import com.sorcerer.jwt_service.repository.ClientDetailsRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Autowired
    private ClientDetailsRepo clientDetailsRepo;

    private SecretKey getSecretKey(String secretKey){
         return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public JwtResponseDto generateToken(String account, String username){
        ClientDetails clientDetails=clientDetailsRepo.findByAccount(account).orElse(null);
        if(clientDetails==null) throw new IllegalArgumentException("account "+account+" does not exist");
        final String secretKey=clientDetails.getSecretKey();
        System.out.println(secretKey);
        String token=Jwts.builder()
                .subject(username)
                .claim("Username",username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*15))
                .signWith(getSecretKey(secretKey))
                .compact();
        return JwtResponseDto.builder()
                .status("Token Generated")
                .token(token)
                .userName(username)
                .generatedAt(new Date(System.currentTimeMillis()))
                .validTill(new Date(System.currentTimeMillis()+1000*60*15)).build();
    }

    public JwtResponseDto validateToken(String account, String token) {
        ClientDetails clientDetails=clientDetailsRepo.findByAccount(account).orElse(null);
        if(clientDetails==null) throw new IllegalArgumentException("account "+account+" does not exist");
        Claims claims=Jwts.parser()
                .verifyWith(getSecretKey(clientDetails.getSecretKey()))
                .build()
                .parseSignedClaims(token)
                .getPayload();

            return JwtResponseDto.builder().status("Valid Token")
                    .userName(claims.getSubject())
                    .token(token)
                    .generatedAt(claims.getIssuedAt())
                    .validTill( claims.getExpiration())
                    .build();




    }
}
