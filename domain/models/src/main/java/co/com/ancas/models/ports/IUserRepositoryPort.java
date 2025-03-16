package co.com.ancas.models.ports;

import co.com.ancas.models.domain.UserInformation;
import co.com.ancas.models.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserRepositoryPort {
    boolean existsByEmail(String s);
    void save(Users userTosave);
    Optional<Users> findById(String id);
    Page<UserInformation> findUsersExceptMe(String userId, Pageable pageable);
}
