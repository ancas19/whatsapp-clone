package co.com.ancas.models.domain;

import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Messages {
    private Long id;
    private String content;
    private MessageState state;
    private MessageType type;
    private String senderId;
    private String recipientId;
    private Chat chat;
    private String mediaFilePath;
}
