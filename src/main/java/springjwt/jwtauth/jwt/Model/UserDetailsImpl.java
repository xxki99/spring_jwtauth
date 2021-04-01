package springjwt.jwtauth.jwt.Model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import springjwt.jwtauth.jwt.Entity.UserProfile;



public class UserDetailsImpl implements UserDetails {
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String password;

    private String username;

    public static UserDetailsImpl build(UserProfile user){
        return new UserDetailsImpl(user.getUsername(), user.getPassword());
    }

    public UserDetailsImpl(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
