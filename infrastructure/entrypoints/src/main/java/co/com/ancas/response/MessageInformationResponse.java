package co.com.ancas.response;

import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageInformationResponse implements Serializable {
    private Long id;
    private String content;
    private MessageType type;
    private MessageState state;
    private String senderId;
    private String receiverId;
    private LocalDateTime createdAt;
    private String mediaFilePath;
}
