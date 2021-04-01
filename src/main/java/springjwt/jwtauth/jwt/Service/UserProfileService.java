package springjwt.jwtauth.jwt.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springjwt.jwtauth.jwt.Entity.UserProfile;
import springjwt.jwtauth.jwt.Payload.Request.RegisterUserForm;
import springjwt.jwtauth.jwt.Repository.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepo;

    public Optional<UserProfile> findUserByUsername(String username){
        return userProfileRepo.findByUsername(username);
    }

    public UserProfile registerUser(RegisterUserForm form){
        UserProfile user = new UserProfile(form.getUsername(), form.getPassword(), form.getEmail());
        UserProfile newUser = userProfileRepo.save(user);
        return newUser;
    }
}
