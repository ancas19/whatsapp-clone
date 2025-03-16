package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.UserInformation;
import co.com.ancas.models.domain.Users;
import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.ports.IUserRepositoryPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@RequiredArgsConstructor
public class UserAdapter implements IUserPorts {
    private final IUserRepositoryPort  userRepositoryPort;

    @Override
    public void synchronizeUser(Users userTosave) {
        if(this.userRepositoryPort.existsByEmail(userTosave.getEmail())){
            verifyChanges(userTosave);
            return;
        }
        this.userRepositoryPort.save(userTosave);
    }

    private void verifyChanges(Users userTosave) {
        Users userFound = this.findById(userTosave.getId());
        if(userFound.getFirstName().equals(userTosave.getFirstName()) || userFound.getLastName().equals(userTosave.getLastName())){
            userFound.setFirstName(userTosave.getFirstName());
            userFound.setLastName(userTosave.getLastName());
            this.userRepositoryPort.save(userFound);
        }
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
    public Page<UserInformation> findUsersExceptMe(String userId, Pageable pageable) {
        return this.userRepositoryPort.findUsersExceptMe(userId,pageable);
    }
}
