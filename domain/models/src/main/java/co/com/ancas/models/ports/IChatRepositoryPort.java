package co.com.ancas.models.ports;

import co.com.ancas.models.domain.Chat;
import co.com.ancas.models.domain.ChatInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IChatRepositoryPort {
    Page<ChatInformation> getChatInformationByReceiverId(String currentuser, Pageable pageable);
    Optional<String> getChatByReceiverAndSender(String serderId, String receiverId);
    Chat save(Chat chatToSave);
    Optional<Chat> findById(String chatId);
}
