package users.userservice.Services;

import org.springframework.http.ResponseEntity;
import users.userservice.Dtos.SignUpRequestDto;
import users.userservice.Dtos.UserDto;
import users.userservice.Exceptions.NotFoundException;
import users.userservice.Exceptions.UserAlreadyExistException;
import users.userservice.Models.SessionStatus;

public interface AuthService {

    UserDto signUp(String email, String password) throws UserAlreadyExistException;
    ResponseEntity<UserDto> logIn(String email, String password) throws NotFoundException;
    void logOut(String token, Long userId) throws NotFoundException;
     SessionStatus validate(String token, Long userId);

}
