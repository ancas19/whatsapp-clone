package co.com.ancas.models.domain;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Chat {
    private String id;
    private Users sender;
    private Users recipient;
    private List<Messages> messages;
    private String chatName;
    private Long unreadMessages;
    private Messages lastMessage;
    private LocalDateTime lastMessageDate;
}
