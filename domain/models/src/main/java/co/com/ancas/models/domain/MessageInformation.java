package co.com.ancas.models.domain;

import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageInformation {
    private Long id;
    private String content;
    private MessageType type;
    private MessageState state;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    private String mediaFilePath;
}
