package users.userservice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.userservice.Dtos.SetUserRolesRequestDto;
import users.userservice.Dtos.UserDto;
import users.userservice.Exceptions.NotFoundException;
import users.userservice.Services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long userId) throws NotFoundException {
        UserDto userDto = userService.getUserDetails(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") Long userId, @RequestBody SetUserRolesRequestDto requestDto) throws NotFoundException {
        UserDto userDto = userService.setUserRoles(userId, requestDto.getRoleIds());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
