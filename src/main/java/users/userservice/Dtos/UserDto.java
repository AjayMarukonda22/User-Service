package users.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;
import users.userservice.Models.Role;
import users.userservice.Models.User;

import java.util.Set;
@Getter
@Setter
public class UserDto {
    private String email;
    private Set<Role> roleSet;
    public static UserDto from(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }
}
