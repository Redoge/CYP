package testapp.redoge.cyp.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import testapp.redoge.cyp.service.UserDetailsImpl;

import java.util.Date;

@Component
public class JwtUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPricipal = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(jwtSecret);
        return Jwts.builder().setSubject((userPricipal.getUsername())).setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

    }
    public boolean validateJwtToken(String jwt){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        }catch (MalformedJwtException | IllegalArgumentException e){
            System.out.println(e.getMessage()); //todo: add logger
        }
        return false;
    }
    public String getUsernameFromJwtToken(String jwt){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
    }

}
