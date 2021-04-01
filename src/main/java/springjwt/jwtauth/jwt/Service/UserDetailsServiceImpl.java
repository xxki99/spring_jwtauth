package springjwt.jwtauth.jwt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springjwt.jwtauth.jwt.Entity.UserProfile;
import springjwt.jwtauth.jwt.Model.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserProfileService userProfileService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userProfileService.findUserByUsername(username)
            .orElseThrow(()->new UsernameNotFoundException("Username not found: " + username));

        return UserDetailsImpl.build(user);

    }
    
}
