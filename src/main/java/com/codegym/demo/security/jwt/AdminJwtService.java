package com.codegym.demo.security.jwt;

import com.codegym.demo.security.principal.AdminPrinciple;
import com.codegym.demo.security.principal.UserPrinciple;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Component
@Service
public class AdminJwtService {
    private static final String SECRET_KEY = "123456789";
    private static final long EXPIRE_TIME = 86400000000L;
    private static final Logger logger = LoggerFactory.getLogger(UserJwtService.class.getName());

    public String generateTokenLogin(Authentication authentication) {
        AdminPrinciple adminPrinciple = (AdminPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((adminPrinciple.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
