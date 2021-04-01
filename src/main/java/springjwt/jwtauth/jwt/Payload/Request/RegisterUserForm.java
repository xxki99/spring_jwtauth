package springjwt.jwtauth.jwt.Payload.Request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class RegisterUserForm {

    @Getter
    @Setter
    @NotBlank(message = "Username is empty")
    private String username;

    @Getter
    @Setter
    @NotBlank(message = "Password is empty")
    private String password;

    @Getter
    @Setter
    @NotBlank(message = "Email is empty")
    private String email;

}
