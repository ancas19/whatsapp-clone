package co.com.ancas.models.ports;

import co.com.ancas.models.domain.Users;

public interface IUserRepositoryPort {

    boolean existsByEmail(String s);
    void save(Users userTosave);
}
