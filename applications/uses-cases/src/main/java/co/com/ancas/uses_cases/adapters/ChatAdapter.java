package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.*;
import co.com.ancas.models.ports.IChatRepositoryPort;
import co.com.ancas.uses_cases.ports.IChatPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ChatAdapter implements IChatPort {
    private final IChatRepositoryPort chatRepositoryPort;
    private final IUserPorts userPorts;


    @Override
    public List<ChatInformation> getChatInformationByReceiverId(String currentuser) {
        return chatRepositoryPort.getChatInformationByReceiverId(currentuser);
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
}
