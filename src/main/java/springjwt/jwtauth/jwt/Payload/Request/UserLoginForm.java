package springjwt.jwtauth.jwt.Payload.Request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class UserLoginForm {
    
    @Getter
    @Setter
    @NotBlank(message = "Username cannot be null")
    private String username;

    @Getter
    @Setter
    @NotBlank(message = "Password cannot be null")
    private String password;

}
