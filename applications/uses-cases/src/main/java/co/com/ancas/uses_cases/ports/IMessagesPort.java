package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IMessagesPort {
    void createMessage(MessageCreation messageCreation);
    Page<MessageInformation> findMessagesByChatId(String chatId, Pageable pageable);
    void setMessagesToSeen(ChatStatus chatStatus);
    void uploadFile(MediaMessage mediaMessage) throws IOException;
}
