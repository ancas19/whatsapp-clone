package co.com.ancas.postgres.adapters;

import co.com.ancas.models.domain.Chat;
import co.com.ancas.models.domain.ChatInformation;
import co.com.ancas.models.domain.Messages;
import co.com.ancas.models.domain.Users;
import co.com.ancas.models.ports.IChatRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.ChatEntity;
import co.com.ancas.postgres.entities.UserEntity;
import co.com.ancas.postgres.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRepositoryAdapter implements IChatRepositoryPort {
    private final ChatRepository chatRepository;

    @Override
    public List<ChatInformation> getChatInformationByReceiverId(String currentuser) {
        return this.chatRepository.findChatBySenderId(currentuser)
                .stream()
                .map(chat -> mapToChatInformation(chat, currentuser))
                .toList();
    }

    @Override
    public Optional<String> getChatByReceiverAndSender(String serderId, String receiverId) {
        Optional<ChatEntity> chat = this.chatRepository.findBySenderIdAndRecipientId(serderId, receiverId);
        return chat.map(ChatEntity::getId);
    }

    @Override
    public Chat save(Chat chatToSave) {
        return mapperToChat(this.chatRepository.save(mapToChatEntity(chatToSave)));
    }

    @Override
    public Optional<Chat> findById(String chatId) {
        return this.chatRepository.findById(chatId)
                .map(this::mapperToChat);
    }

    private Chat mapperToChat(ChatEntity save) {
        return Chat.builder()
                .id(save.getId())
                .sender(Mapper.map(save.getSender(), Users.class))
                .recipient(Mapper.map(save.getRecipient(), Users.class))
                .messages(Objects.isNull(save.getMessages())?null:Mapper.mapAll(save.getMessages(), Messages.class))
                .chatname(Objects.isNull(save.getSender())?null:save.getChatName(save.getSender().getId()))
                .build();
    }

    private ChatInformation mapToChatInformation(ChatEntity chat, String currentuser) {
        return ChatInformation.builder()
                .id(chat.getId())
                .name(chat.getChatName(currentuser))
                .unreadCount(chat.getUnreadMessages(currentuser))
                .lastMessage(chat.getLastMessage())
                .lastMessageTime(chat.getLastMessageDate())
                .isRecipientOnline(chat.getRecipient().isUserOnline())
                .senderId(chat.getSender().getId())
                .receiverId(chat.getRecipient().getId())
                .build();
    }

    private ChatEntity mapToChatEntity(Chat chat) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setRecipient(Mapper.map(chat.getRecipient(), UserEntity.class));
        chatEntity.setSender(Mapper.map(chat.getSender(), UserEntity.class));
        return chatEntity;
    }
}
