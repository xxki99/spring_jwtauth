package springjwt.jwtauth.jwt.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springjwt.jwtauth.jwt.Entity.UserProfile;
import springjwt.jwtauth.jwt.Model.UserDetailsImpl;
import springjwt.jwtauth.jwt.Payload.Request.RegisterUserForm;
import springjwt.jwtauth.jwt.Payload.Request.UserLoginForm;
import springjwt.jwtauth.jwt.Payload.Response.UserProfileResponse;
import springjwt.jwtauth.jwt.Service.UserProfileService;
import springjwt.jwtauth.jwt.Utils.JwtUtils;


@RestController
@RequestMapping(value = "/api")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping(value="/index")
    public ResponseEntity<UserProfileResponse> index(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserProfile user = userProfileService.findUserByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return new ResponseEntity<UserProfileResponse>(UserProfileResponse.build(user), HttpStatus.OK);
    }

    @PostMapping(value="/register")
    public UserProfileResponse register(@Valid @RequestBody RegisterUserForm form) {
        UserProfileResponse userResponse = UserProfileResponse.build(userProfileService.registerUser(form));
        return userResponse;
    }

    @PostMapping(value="/login")
    public String login(@Valid @RequestBody UserLoginForm loginForm, HttpServletResponse response) {


        
        // auth
        UsernamePasswordAuthenticationToken passwordAuth = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());


        Authentication auth = authenticationManager.authenticate(passwordAuth);
        
        // create jwt after finding user
        String jwtToken = jwtUtils.generateJwtToken(auth);

        Cookie cookie = new javax.servlet.http.Cookie("Authorization", jwtToken);

        response.addCookie(cookie);

        return jwtToken;


    }
    
    
}
