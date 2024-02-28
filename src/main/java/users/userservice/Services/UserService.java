package users.userservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import users.userservice.Dtos.SetUserRolesRequestDto;
import users.userservice.Dtos.UserDto;
import users.userservice.Exceptions.NotFoundException;
import users.userservice.Models.Role;
import users.userservice.Models.User;
import users.userservice.Repositories.RoleRepository;
import users.userservice.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Primary
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public UserDto getUserDetails(Long userId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if(user == null)
            throw new NotFoundException("The user with " + userId + " is not Found");
        User user1 = user.get();
        return UserDto.from(user1);
    }
    public UserDto setUserRoles(Long userId, List<Long> roleIds) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllById(roleIds);
        if(user.isEmpty()){
            throw new NotFoundException("The user with " + userId + " is not found");
        }
        User user1 = user.get();
       user1.getRoleSet().clear();
       user1.getRoleSet().addAll(roles);
       User savedUser = userRepository.save(user1);
       return UserDto.from(savedUser);
    }
}
