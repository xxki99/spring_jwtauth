package springjwt.jwtauth.jwt.Payload.Response;

import java.util.Hashtable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ErrorHandleResponse {
    @Getter
    @Setter
    private HttpStatus code;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private Hashtable<String, String> errors;
}
