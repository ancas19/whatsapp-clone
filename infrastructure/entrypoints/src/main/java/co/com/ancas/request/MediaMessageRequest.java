package co.com.ancas.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MediaMessageRequest {
    private String chatId;
    private String file;
    private String content;
    private String nameFile;
    private String senderId;
}
