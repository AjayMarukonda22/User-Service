package users.userservice.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class logOutRequestDto {
    private String token;
    private Long userId;
}
