package co.com.ancas.services;

import co.com.ancas.models.domain.Users;
import co.com.ancas.uses_cases.ports.IUserPorts;
import co.com.ancas.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAppService {
    private final IUserPorts userPorts;
    private final UserMapper userMapper;

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


    private Optional<String> getuserEmail(Jwt token) {
        Map<String, Object> attributes = token.getClaims();
        if (attributes.containsKey("email")) {
            return Optional.of(attributes.get("email").toString());
        }
        return Optional.empty();
    }
}
