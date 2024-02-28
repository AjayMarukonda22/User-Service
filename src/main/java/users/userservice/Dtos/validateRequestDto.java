package users.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class validateRequestDto {
    private String token;
    private Long userId;
}
