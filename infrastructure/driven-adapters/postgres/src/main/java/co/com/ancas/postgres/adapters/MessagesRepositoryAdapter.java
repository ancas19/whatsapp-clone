package co.com.ancas.postgres.adapters;

import co.com.ancas.models.domain.MessageInformation;
import co.com.ancas.models.domain.Messages;
import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.ports.IMessageRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.MessageEntity;
import co.com.ancas.postgres.repositories.MessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesRepositoryAdapter implements IMessageRepositoryPort {
    private final MessagesRepository messagesRepository;

    @Override
    public void save(Messages build) {
        this.messagesRepository.save(Mapper.map(build, MessageEntity.class));
    }

    @Override
    public List<MessageInformation> getMessagesByChatId(String chatId) {
        return this.messagesRepository.findByChatId(chatId)
                .stream()
                .map(messageEntity->MessageInformation.builder()
                                .id(messageEntity.getId())
                                .content(messageEntity.getContent())
                                .type(messageEntity.getType())
                                .state(messageEntity.getState())
                                .senderId(messageEntity.getSenderId())
                                .receiverId(messageEntity.getRecipientId())
                                .createdAt(messageEntity.getCreatedDate())
                                .mediaFilePath(messageEntity.getMediaFilePath())
                .build()
                )
                .toList();
    }

    @Override
    public void setMessagesToSeen(String id, MessageState messageState) {
        this.messagesRepository.setMessagesAsReadByChat(id, messageState);
    }
}
