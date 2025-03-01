package co.com.ancas.postgres.entities;


import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "chats")
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity  extends AuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserEntity recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<MessageEntity> messages;

    @Transient
    public String getChatName(final String senderId){
        if(recipient.getId().equals(senderId)){
            return "%s %s".formatted(sender.getFirstName(), sender.getLastName());
        }
        return "%s %s".formatted(recipient.getFirstName(), recipient.getLastName());
    }

    @Transient
    public Long getUnreadMessages(final String userId){
        return messages.stream()
                .filter(message -> message.getRecipientId().equals(userId) && message.getState().equals(MessageState.SENT))
                .count();
    }

    @Transient
    public String getLastMessage(){
        if(Objects.nonNull(messages) && !messages.isEmpty()){
            return messages.getFirst().getType().equals(MessageType.TEXT) ? messages.get(0).getContent() : Constants.ATTACHMENT.getValue();
        }
        return null;
    }

    @Transient
    public LocalDateTime getLastMessageDate(){
        if(Objects.nonNull(messages) && !messages.isEmpty()){
            return messages.getFirst().getCreatedDate();
        }
        return null;
    }
}
