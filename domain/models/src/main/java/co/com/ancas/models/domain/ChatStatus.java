package co.com.ancas.models.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatStatus {
    private String idUser;
    private String idChat;
}
