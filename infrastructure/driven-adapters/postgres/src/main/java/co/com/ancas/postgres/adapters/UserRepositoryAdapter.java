package co.com.ancas.postgres.adapters;

import co.com.ancas.models.domain.Users;
import co.com.ancas.models.ports.IUserRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.UserEntity;
import co.com.ancas.postgres.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements IUserRepositoryPort {
    private final UserRepository userRepository;

    @Override
    public boolean existsByEmail(String s) {
        return this.userRepository.existsByEmail(s);
    }

    @Override
    public void save(Users userTosave) {
        this.userRepository.save(Mapper.map(userTosave, UserEntity.class));
    }
}
