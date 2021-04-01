package springjwt.jwtauth.jwt.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springjwt.jwtauth.jwt.Payload.Request.RegisterUserForm;
import springjwt.jwtauth.jwt.Payload.Response.UserProfileResponse;
import springjwt.jwtauth.jwt.Service.UserProfileService;


@RestController
@RequestMapping(value = "/api")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping(value="/register")
    public UserProfileResponse register(@Valid @RequestBody RegisterUserForm form) {
        UserProfileResponse userResponse = UserProfileResponse.build(userProfileService.registerUser(form));
        return userResponse;
    }
    
}
