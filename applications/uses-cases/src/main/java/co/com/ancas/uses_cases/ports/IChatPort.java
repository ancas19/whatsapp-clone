package co.com.ancas.uses_cases.ports;

import co.com.ancas.models.domain.Chat;
import co.com.ancas.models.domain.ChatCreated;
import co.com.ancas.models.domain.ChatCreation;
import co.com.ancas.models.domain.ChatInformation;

import java.util.List;

public interface IChatPort {
    List<ChatInformation> getChatInformationByReceiverId(String currentuser);
    ChatCreated createChat(ChatCreation chatCreation);
    Chat findChatById(String chatId);

}
