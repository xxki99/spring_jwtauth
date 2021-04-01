package springjwt.jwtauth.jwt.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserProfile {

    public UserProfile(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Getter
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @Getter
    @Setter
    @NotBlank(message = "Username cannot be null.")
    private String username;

    

    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    @NotBlank(message = "Password cannot be null")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    @Getter
    @Setter
    @NotBlank(message = "Email cannot be null.")
    private String email;


    @ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter
    @Setter
	private Set<Role> roles = new HashSet<>();
}
