package co.com.ancas.models.domain;

import co.com.ancas.models.enums.MessageType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageCreation {
    private String content;
    private String senderId;
    private String receiverId;
    private MessageType type;
    private String chatId;
}
