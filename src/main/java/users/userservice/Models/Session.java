package users.userservice.Models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonDeserialize(as = Session.class)
public class Session extends BaseModel{
    private String token;
    private LocalDate expiryDate;
    @ManyToOne
    private User user;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
