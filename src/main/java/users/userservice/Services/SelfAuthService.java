package users.userservice.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import users.userservice.Dtos.SignUpRequestDto;
import users.userservice.Dtos.UserDto;
import users.userservice.Exceptions.NotFoundException;
import users.userservice.Exceptions.UserAlreadyExistException;
import users.userservice.Models.Session;
import users.userservice.Models.SessionStatus;
import users.userservice.Models.User;
import users.userservice.Repositories.SessionRepository;
import users.userservice.Repositories.UserRepository;

import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

@Service("selfUserService")
@Primary
public class SelfAuthService implements AuthService {
private UserRepository userRepository;
private SessionRepository sessionRepository;
private BCryptPasswordEncoder bCryptPasswordEncoder;
private SecretKey secretKey;
@Autowired
public SelfAuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.sessionRepository = sessionRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  secretKey = Jwts.SIG.HS256.key().build();
}
    @Override
    public UserDto signUp(String email, String password) throws UserAlreadyExistException {
    Optional<User> optionalUser = userRepository.findUserByEmail(email);
    if(optionalUser.isPresent()) {
        throw new UserAlreadyExistException("The User with " + email + " already Exists.");
    }
    User user1 = new User();
    user1.setEmail(email);
    user1.setPassword(bCryptPasswordEncoder.encode(password));
    User savedUser = userRepository.save(user1);
    return UserDto.from(savedUser);
    }

    @Override
    public ResponseEntity<UserDto> logIn(String email, String password) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if(optionalUser.isEmpty()){
            throw new NotFoundException("The User is Not Found. Can you please SignUp");
        }
        User user = optionalUser.get();
        if(!bCryptPasswordEncoder.matches(password, user.getPassword()))
            throw new RuntimeException("The password is incorrect.");

//            String token = RandomStringUtils.randomAlphabetic(30);
        Map<String, Object> jwtData = new HashMap<>();
        jwtData.put("email", email);
        jwtData.put("created_at", java.sql.Date.valueOf(LocalDate.now()));
        jwtData.put("expiry_at", java.sql.Date.valueOf(LocalDate.now().plusDays(3)));

String token = Jwts.builder()
        .claims(jwtData)
        .signWith(secretKey)
        .compact();

          Session session = new Session();
          session.setToken(token);
          session.setUser(user);
        LocalDate currentDate = LocalDate.now();
          session.setExpiryDate(currentDate.plusDays(3));
          session.setSessionStatus(SessionStatus.ACTIVE);
          sessionRepository.save(session);
          UserDto userDto = UserDto.from(user);
        MultiValueMap<String, String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE, "auth-token: " + token);
        return new ResponseEntity<>(userDto, headers, HttpStatus.OK);
    }

    @Override
    public void logOut(String token, Long userId) throws NotFoundException {
        Optional<Session> optionalSession = sessionRepository
                .findSessionByTokenAndUser(token, userId);
        if(optionalSession.isEmpty())
            throw new NotFoundException("The token is invalid or the details are mismatching");
        Session session = optionalSession.get();
        session.setSessionStatus(SessionStatus.ENDED);
        sessionRepository.save(session);
        return ;
    }

    @Override
    public SessionStatus validate(String token, Long userId){
        Optional<Session> optionalSession = sessionRepository
                .findSessionByTokenAndUser(token, userId);
        if(optionalSession.isEmpty())
            return SessionStatus.ENDED;
        Session session = optionalSession.get();
        if(session.getExpiryDate().isBefore(LocalDate.now()) || session.getSessionStatus() != SessionStatus.ACTIVE)
            return SessionStatus.ENDED;

        Jws<Claims> claimsJws = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        String email = (String) claimsJws.getPayload().get("email");
        Long expiryAt = (Long) claimsJws.getPayload().get("expiry_at");
        System.out.println("Email " + email);
        System.out.println("Expiry At " + expiryAt);
        return SessionStatus.ACTIVE;
    }
}
