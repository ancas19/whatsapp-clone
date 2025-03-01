package co.com.ancas.uses_cases.adapters;

import co.com.ancas.models.domain.*;
import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import co.com.ancas.models.ports.ICloudinaryPort;
import co.com.ancas.models.ports.IMessageRepositoryPort;
import co.com.ancas.uses_cases.ports.IChatPort;
import co.com.ancas.uses_cases.ports.IMessagesPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MessagesAdapter implements IMessagesPort {
    private final IMessageRepositoryPort messageRepositoryPort;
    private final ICloudinaryPort cloudinaryPort;
    private final IUserPorts userPorts;
    private final IChatPort chatPorts;

    @Override
    public void createMessage(MessageCreation messageCreation) {
        Chat chatFound = chatPorts.findChatById(messageCreation.getChatId());
        Users senderFound = userPorts.findById(messageCreation.getSenderId());
        Users recipientFound = userPorts.findById(messageCreation.getReceiverId());
        messageRepositoryPort.save(
                Messages.builder()
                        .content(messageCreation.getContent())
                        .state(MessageState.SENT)
                        .type(messageCreation.getType())
                        .senderId(senderFound.getId())
                        .recipientId(recipientFound.getId())
                        .chat(chatFound)
                        .build()
        );

    }

    @Override
    public List<MessageInformation> findMessagesByChatId(String chatId) {
        return this.messageRepositoryPort.getMessagesByChatId(chatId);
    }

    @Override
    public void setMessagesToSeen(ChatStatus chatStatus) {
        Chat chatFound = chatPorts.findChatById(chatStatus.getIdChat());
        String recipientId = getRecipientId(chatFound, chatStatus.getIdUser());
        messageRepositoryPort.setMessagesToSeen(chatFound.getId(), MessageState.SEEN);

    }

    @Override
    public void uploadFile(MediaMessage mediaMessage) throws IOException {
        Chat chatFound = chatPorts.findChatById(mediaMessage.getChatId());
        String senderId = getSenderId(chatFound, mediaMessage.getSenderId());
        String recipientId = getRecipientId(chatFound, mediaMessage.getSenderId());
        Map mediaUploaded = cloudinaryPort.uploadFile(mediaMessage);
        messageRepositoryPort.save(
                Messages.builder()
                        .content(mediaMessage.getContent())
                        .state(MessageState.SENT)
                        .recipientId(recipientId)
                        .senderId(senderId)
                        .type(MessageType.IMAGE)
                        .senderId(senderId)
                        .recipientId(recipientId)
                        .chat(chatFound)
                        .mediaFilePath(mediaUploaded.get("url").toString())
                        .build()
        );

    }

    private String getSenderId(Chat chat, String idUser) {
        if (chat.getSender().getId().equals(idUser)) {
            return chat.getSender().getId();
        }
        return chat.getRecipient().getId();
    }
    private String getRecipientId(Chat chatFound, String idUser) {
        if (chatFound.getSender().getId().equals(idUser)) {
            return chatFound.getRecipient().getId();
        }
        return chatFound.getSender().getId();
    }
}
