package springjwt.jwtauth.jwt;

import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import springjwt.jwtauth.jwt.Controller.UserProfileController;

@AutoConfigureJsonTesters
@WebMvcTest(UserProfileController.class)
public class UserProfileControllerJUnitTest{

}