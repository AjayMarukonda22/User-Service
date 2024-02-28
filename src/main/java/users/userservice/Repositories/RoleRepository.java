package users.userservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.userservice.Models.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
