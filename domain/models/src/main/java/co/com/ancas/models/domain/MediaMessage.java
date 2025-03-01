package co.com.ancas.models.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MediaMessage {
    private String chatId;
    private String file;
    private String content;
    private String nameFile;
    private String senderId;
}
