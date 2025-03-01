package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.UserInformation;
import co.com.ancas.models.domain.Users;
import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.ports.IUserRepositoryPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    @Override
    public Users findById(String id) {
        return this.userRepositoryPort.findById(id)
                .orElseThrow(() -> new NotFoundException(MessagesData.USER_NOT_FOUND.getMessage()));
    }

    @Override
    public List<UserInformation> findUsersExceptMe(String userId) {
        return this.userRepositoryPort.findUsersExceptMe(userId);
    }
}
