package users.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class logInRequestDto {
    private String email;
    private String password;
}
