package com.perscholas.groupupspringbootbackend.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtEncoder jwtEncoder;
    
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;
    
    //
    public String generateToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return generateTokenWithUserName(principal.getUsername());
    }

    public String generateTokenWithUserName(String username) {
        JwtClaimsSet claims = JwtClaimsSet.builder() //creates a new empty JWT claims set
                .issuer("self") //set the issuer as self
                .issuedAt(Instant.now()) //date/time issued
                .expiresAt(Instant.now().plusSeconds(jwtExpirationInMillis))//expiration time
                .subject(username) //sets the subject of the claim to the passed variable username
                .claim("scope", "ROLE_USER")
                .build();//build the claim set

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;//expiration time set in app.properties
    }
}
