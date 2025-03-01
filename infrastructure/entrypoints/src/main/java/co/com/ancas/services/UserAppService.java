package co.com.ancas.services;

import co.com.ancas.models.domain.UserInformation;
import co.com.ancas.models.domain.Users;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.uses_cases.ports.IUserPorts;
import co.com.ancas.util.CurrentUser;
import co.com.ancas.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAppService {
    private final IUserPorts userPorts;
    private final UserMapper userMapper;
    private final CurrentUser currentUser;

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public void synchronizeUser(Jwt token) {
        Optional<String> email = getuserEmail(token);
        if (email.isEmpty()){
            return;
        }
        if(userPorts.existsByEmail(email.get())){
            return;
        }
        Users userTosave=userMapper.fromTokenAttributes(token.getClaims());
        userTosave.setEmail(email.get());
        userPorts.synchronizeUser(userTosave);
    }

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public List<UserInformationResponse> findUsersExceptMe() {
        return Mapper.mapAll(userPorts.findUsersExceptMe(currentUser.getCurrentUserId()), UserInformationResponse.class);
    }


    private Optional<String> getuserEmail(Jwt token) {
        Map<String, Object> attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.of(attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
