package co.com.ancas.models.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatCreation {
    private String senderId;
    private String recipientId;
}
