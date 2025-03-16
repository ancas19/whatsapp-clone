package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.*;
import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.ports.IChatRepositoryPort;
import co.com.ancas.uses_cases.ports.IChatPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChatAdapter implements IChatPort {
    private final IChatRepositoryPort chatRepositoryPort;
    private final IUserPorts userPorts;


    @Override
    public Page<ChatInformation> getChatInformationByReceiverId(String currentuser, Pageable pageable) {
        return chatRepositoryPort.getChatInformationByReceiverId(currentuser,pageable);
    }

    @Override
    public ChatCreated createChat(ChatCreation chatCreation) {
        Optional<String> chatId = chatRepositoryPort.getChatByReceiverAndSender(chatCreation.getSenderId(), chatCreation.getRecipientId());
        if (chatId.isPresent()) {
            return new ChatCreated(chatId.get());
        }
        Users senderFound=userPorts.findById(chatCreation.getSenderId());
        Users recipientFound=userPorts.findById(chatCreation.getRecipientId());
        Chat chatToSave = Chat.builder()
                .sender(senderFound)
                .recipient(recipientFound)
                .build();
        Chat chatSaved = chatRepositoryPort.save(chatToSave);
        return new ChatCreated(chatSaved.getId());
    }

    @Override
    public Chat findChatById(String chatId) {
        return chatRepositoryPort.findById(chatId)
                .orElseThrow(() -> new NotFoundException(MessagesData.CHAT_NOT_FOUND.getMessage()));
    }
}
