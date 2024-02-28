package users.userservice.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import users.userservice.Models.Role;
import users.userservice.Repositories.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        Role savedRole = roleRepository.save(role);
        return savedRole;
    }
}
