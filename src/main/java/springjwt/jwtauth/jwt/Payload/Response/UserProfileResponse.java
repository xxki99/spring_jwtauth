package springjwt.jwtauth.jwt.Payload.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springjwt.jwtauth.jwt.Entity.UserProfile;

@AllArgsConstructor
public class UserProfileResponse {
    @Getter
    @Setter
    String username;

    @Getter
    @Setter
    String email;

    public static UserProfileResponse build(UserProfile user){
        return new UserProfileResponse(user.getUsername(), user.getEmail());
    }
}
