package co.com.ancas.models.ports;

import co.com.ancas.models.domain.Users;

import java.util.Optional;

public interface IUserRepositoryPort {

    boolean existsByEmail(String s);
    void save(Users userTosave);

    Optional<Users> findById(String id);
}
