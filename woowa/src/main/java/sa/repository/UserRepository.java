package sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
