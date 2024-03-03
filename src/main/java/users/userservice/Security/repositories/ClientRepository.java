package users.userservice.Security.repositories;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.userservice.Security.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}
