package springjwt.jwtauth.jwt.Utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import springjwt.jwtauth.jwt.Model.UserDetailsImpl;

public class JwtUtils {

    private Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwtauth.jwtSecret}")
    private String jwtSecret;

    @Value("${jwtauth.jwtExpirationMs}")
    private int duration;

    public String generateJwtToken(Authentication auth){
        // Get principal from authentication object
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        // build jwt token here
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime() + duration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUsernameFromToken(String token){

        return Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateJwtToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (ExpiredJwtException e){

            logger.info("Token is expried");
        }
        catch (SignatureException e){

            logger.info("Signature is invalid");
        }
        catch (UnsupportedJwtException e){

            logger.info("unsupported token");
        }
        catch (MalformedJwtException e){

            logger.info("Malformed token");
        }
        catch (NullPointerException e){

            logger.info("Null pointer" + e.getMessage());
        }
        return false;
    }
}
