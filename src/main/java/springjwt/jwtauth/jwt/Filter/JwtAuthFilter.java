package springjwt.jwtauth.jwt.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import springjwt.jwtauth.jwt.Service.UserDetailsServiceImpl;
import springjwt.jwtauth.jwt.Utils.JwtUtils;

public class JwtAuthFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceImpl userService;

    @Autowired
    AuthenticationManager authManager;

    // create Logger for loging message
    private final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // get jwt token header from request
        Cookie[] cookies = request.getCookies();

        String token = null;

        if (cookies != null){

            for (Cookie cookie : cookies){
                if (cookie.getName().equals("Authorization")){

                    token = cookie.getValue();
                }
            }

        }



        // extract token from jwtTokenHeader
        if (token != null){


                try{
                    if (jwtUtils.validateJwtToken(token)){

                        UserDetails userDetails = userService.loadUserByUsername(jwtUtils.getUsernameFromToken(token));


                        // create authenication object
                        UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        userAuth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContext sc = SecurityContextHolder.getContext();
                        sc.setAuthentication(userAuth);

                        
                        logger.info("Set authentication");


                    }



                }
                catch(NullPointerException e){
                    // logger.error("NullPointerException in filter: " + e.getMessage());
                }
                catch(UsernameNotFoundException e){

                    
                    logger.info("Error occurs when trying authentication token: " + e.getMessage());
                }
                

            
        }


        filterChain.doFilter(request, response);
    }

    
}
