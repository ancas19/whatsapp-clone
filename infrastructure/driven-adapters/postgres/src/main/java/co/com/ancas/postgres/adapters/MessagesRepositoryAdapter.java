package co.com.ancas.postgres.adapters;

import co.com.ancas.models.ports.IMessageRepositoryPort;
import co.com.ancas.postgres.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagesRepositoryAdapter implements IMessageRepositoryPort {
    private final MessagesRepository messagesRepository;
}
