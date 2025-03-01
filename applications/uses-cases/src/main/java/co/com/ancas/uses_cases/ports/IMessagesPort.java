package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.*;

import java.io.IOException;
import java.util.List;

public interface IMessagesPort {
    void createMessage(MessageCreation messageCreation);
    List<MessageInformation> findMessagesByChatId(String chatId);
    void setMessagesToSeen(ChatStatus chatStatus);
    void uploadFile(MediaMessage mediaMessage) throws IOException;
}
