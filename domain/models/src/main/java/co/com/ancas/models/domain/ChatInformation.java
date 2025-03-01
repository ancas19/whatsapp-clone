package co.com.ancas.models.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatInformation {
    private String id;
    private String name;
    private long unreadCount;
    private String lastMessage;
    private LocalDateTime lastMessageTime;
    private boolean isRecipientOnline;
    private String senderId;
    private String receiverId;
}
