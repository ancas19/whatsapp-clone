package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.Users;

public interface IUserPorts {
    void synchronizeUser(Users userTosave);
    boolean existsByEmail(String s);
    Users findById(String id);
}
