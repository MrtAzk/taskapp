package com.mert.taskmanagement.taskapp.business.concretes;

import com.mert.taskmanagement.taskapp.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {


    private final static String SECRET_KEY ="45284B2B6250655368566D597133743677397A24432646294A404E635266556A";

    public String findUserName(String token){
        return exportToken(token, Claims::getSubject);
    }

    private  <T>  T exportToken(String token, Function<Claims,T> claimsTFunction){
        Claims claims = Jwts.parser().setSigningKey(getkey()).build().parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }

    private Key getkey() {
        byte[] key= Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }

    public boolean tokenControl(String jwt, UserDetails userDetails) {
        final String userName=findUserName(jwt);
        return (userName.equals(userDetails.getUsername())&& !exportToken(jwt,Claims::getExpiration).before(new Date()));
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(user.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getkey(), SignatureAlgorithm.HS256)
                .compact();
    }
}

