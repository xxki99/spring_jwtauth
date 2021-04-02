package springjwt.jwtauth.jwt.Model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import springjwt.jwtauth.jwt.Entity.Role;
import springjwt.jwtauth.jwt.Entity.UserProfile;



public class UserDetailsImpl implements UserDetails {
    

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String password;

    private String username;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserProfile user){

        Set<Role> roles = user.getRoles();


        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role: roles){

            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities){
        this.username = username;
        this.password = password;

        this.authorities = authorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
