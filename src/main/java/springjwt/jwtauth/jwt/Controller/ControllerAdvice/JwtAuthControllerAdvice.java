package springjwt.jwtauth.jwt.Controller.ControllerAdvice;

import java.util.Hashtable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import springjwt.jwtauth.jwt.Payload.Response.ErrorHandleResponse;

@ControllerAdvice
public class JwtAuthControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorHandleResponse> handleInvalidFiledRequest(MethodArgumentNotValidException e){
        Hashtable<String, String> errorMessages = new Hashtable<String, String>();
        for (FieldError error : e.getFieldErrors()){

            String field = error.getField();
            String code = error.getCode();

            errorMessages.put(field, code);
        }
        ErrorHandleResponse errroResponse = new ErrorHandleResponse(HttpStatus.BAD_REQUEST, "Invalid request field value", errorMessages);
        return new ResponseEntity<ErrorHandleResponse>(errroResponse, HttpStatus.BAD_REQUEST);
    }
}
