package co.com.ancas.models.ports;

import co.com.ancas.models.domain.MessageInformation;
import co.com.ancas.models.domain.Messages;
import co.com.ancas.models.enums.MessageState;

import java.util.List;

public interface IMessageRepositoryPort {
    void save(Messages build);
    List<MessageInformation> getMessagesByChatId(String chatId);
    void setMessagesToSeen(String id, MessageState messageState);
}
