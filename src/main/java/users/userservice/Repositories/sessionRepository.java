package users.userservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import users.userservice.Models.Session;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
  @Query(value = "select * from Session where Session.token = :token and Session.user_id = :userId", nativeQuery = true)
    public Optional<Session> findSessionByTokenAndUser(String token, Long userId);
}
