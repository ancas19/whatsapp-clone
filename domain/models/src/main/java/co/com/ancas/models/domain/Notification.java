package co.com.ancas.models.domain;

import co.com.ancas.models.enums.MessageType;
import co.com.ancas.models.enums.NotificationType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Notification {
    private String chatId;
    private String content;
    private String senderId;
    private String receiverId;
    private String chatName;
    private MessageType messageType;
    private NotificationType type;
    private String mediaFilePath;

    @Override
    public String toString() {
        return "Notification{" +
                "chatId='" + chatId + '\'' +
                ", content='" + content + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", chatName='" + chatName + '\'' +
                ", messageType=" + messageType +
                ", type=" + type +
                ", mediaFilePath='" + mediaFilePath + '\'' +
                '}';
    }
}
