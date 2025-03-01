package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.Users;
import co.com.ancas.models.ports.IUserRepositoryPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAdapter implements IUserPorts {
    private final IUserRepositoryPort  userRepositoryPort;

    @Override
    public void synchronizeUser(Users userTosave) {
        this.userRepositoryPort.save(userTosave);
    }

    @Override
    public boolean existsByEmail(String s) {
        return this.userRepositoryPort.existsByEmail(s);
    }
}
