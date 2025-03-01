package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.ports.IMessageRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagesAdapter {
    private final IMessageRepositoryPort messageRepositoryPort;
}
