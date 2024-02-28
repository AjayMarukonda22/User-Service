package users.userservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import users.userservice.Dtos.*;
import users.userservice.Exceptions.NotFoundException;
import users.userservice.Exceptions.UserAlreadyExistException;
import users.userservice.Models.SessionStatus;
import users.userservice.Services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistException {
        UserDto userDto = authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/logIn")
    public ResponseEntity<UserDto> logIn(@RequestBody logInRequestDto requestDto) throws NotFoundException {
                   return authService.logIn(requestDto.getEmail(), requestDto.getPassword());
    }

    @PostMapping("/logOut")
    public void logOut(@RequestBody logOutRequestDto requestDto) throws NotFoundException {
        authService.logOut(requestDto.getToken(), requestDto.getUserId());
    }

    @PostMapping("/validate")
    public SessionStatus Validate(@RequestBody validateRequestDto requestDto) {
     return authService.validate(requestDto.getToken(), requestDto.getUserId());
    }
}
