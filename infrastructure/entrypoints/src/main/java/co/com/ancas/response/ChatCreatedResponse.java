package co.com.ancas.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatCreatedResponse {
    private String chatName;
}
