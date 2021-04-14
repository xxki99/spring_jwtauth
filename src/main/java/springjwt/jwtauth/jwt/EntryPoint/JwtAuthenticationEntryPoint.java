package springjwt.jwtauth.jwt.EntryPoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        
        logger.error("Unauthorized Error: {}", authException.getMessage());

        

        SecurityContext sc = SecurityContextHolder.getContext();
        Authentication principal = sc.getAuthentication();
        if (principal == null){
            logger.info("Authenication is null");
        }
        else{
            logger.info("principal = " + principal.getPrincipal());
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);

        
    }
    
}
