package users.userservice.Exceptions;

import lombok.Getter;
import lombok.Setter;

public class NotFoundException extends Exception{
    private String message;
    public NotFoundException(String message){
        super(message);
    }
}
