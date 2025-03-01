package co.com.ancas.models.ports;

import co.com.ancas.models.domain.Chat;
import co.com.ancas.models.domain.ChatInformation;

import java.util.List;
import java.util.Optional;

public interface IChatRepositoryPort {
    List<ChatInformation> getChatInformationByReceiverId(String currentuser);
    Optional<String> getChatByReceiverAndSender(String serderId, String receiverId);
    Chat save(Chat chatToSave);
    Optional<Chat> findById(String chatId);
}
