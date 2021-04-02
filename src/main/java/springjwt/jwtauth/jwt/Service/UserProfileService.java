package springjwt.jwtauth.jwt.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springjwt.jwtauth.jwt.Entity.Role;
import springjwt.jwtauth.jwt.Entity.UserProfile;
import springjwt.jwtauth.jwt.Model.ERole;
import springjwt.jwtauth.jwt.Payload.Request.RegisterUserForm;
import springjwt.jwtauth.jwt.Repository.RoleRepository;
import springjwt.jwtauth.jwt.Repository.UserProfileRepository;

@Service
public class UserProfileService {

    private Logger logger = LoggerFactory.getLogger(UserProfileService.class);

    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    public Optional<UserProfile> findUserByUsername(String username){
        return userProfileRepo.findByUsername(username);
    }

    public boolean existsByUsername(String username){
        return userProfileRepo.existsByUsername(username);
    }


    public UserProfile registerUser(RegisterUserForm form){
        String password = encoder.encode(form.getPassword());

        // create role for new user, all set to ROLE_USER
        Role userRole = roleRepo.findByName(ERole.ROLE_USER).orElseThrow(()-> new RuntimeException("Error: Role not found: " + ERole.ROLE_USER.toString())); 

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        UserProfile user = new UserProfile(form.getUsername(), password, form.getEmail(), roles);
        UserProfile newUser = userProfileRepo.save(user);
        return newUser;
    }
}
