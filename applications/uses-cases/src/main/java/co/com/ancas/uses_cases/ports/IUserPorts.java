package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.UserInformation;
import co.com.ancas.models.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserPorts {
    void synchronizeUser(Users userTosave);
    boolean existsByEmail(String s);
    Users findById(String id);
    Page<UserInformation> findUsersExceptMe(String userId, Pageable pageable);
}
