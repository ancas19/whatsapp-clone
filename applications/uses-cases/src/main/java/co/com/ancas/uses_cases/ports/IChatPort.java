package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.Chat;
import co.com.ancas.models.domain.ChatCreated;
import co.com.ancas.models.domain.ChatCreation;
import co.com.ancas.models.domain.ChatInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IChatPort {
    Page<ChatInformation> getChatInformationByReceiverId(String currentuser, Pageable pageable);
    ChatCreated createChat(ChatCreation chatCreation);
    Chat findChatById(String chatId);

}
