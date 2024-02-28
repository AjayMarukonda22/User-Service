package users.userservice.Exceptions;

import lombok.Getter;
import lombok.Setter;


public class UserAlreadyExistException extends Exception{
    private String message;
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
