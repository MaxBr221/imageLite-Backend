package github.com.MaxBr221.imageliteapi.InfraRepository;

import github.com.MaxBr221.imageliteapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
