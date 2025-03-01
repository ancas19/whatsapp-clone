package co.com.ancas.postgres.adapters;

import co.com.ancas.models.ports.IChatRepositoryPort;
import co.com.ancas.postgres.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements IChatRepositoryPort {
    private final ChatRepository chatRepository;
}
