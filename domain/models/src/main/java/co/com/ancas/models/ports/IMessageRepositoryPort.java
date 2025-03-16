package co.com.ancas.models.ports;

import co.com.ancas.models.domain.MessageInformation;
import co.com.ancas.models.domain.Messages;
import co.com.ancas.models.enums.MessageState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMessageRepositoryPort {
    void save(Messages build);
    Page<MessageInformation> getMessagesByChatId(String chatId, Pageable pageable);
    void setMessagesToSeen(String id, MessageState messageState);
}
