package users.userservice.Security;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import users.userservice.Models.User;
import users.userservice.Repositories.UserRepository;

import java.util.Optional;

@Service
@Getter
@Setter
@JsonDeserialize(as = CustomUserDetailsService.class)
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);
        if(optionalUser.isEmpty())
            throw new UsernameNotFoundException("The username is not found");
        User user = optionalUser.get();
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}
