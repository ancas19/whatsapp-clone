package co.com.ancas.models.domain;

import lombok.*;

import java.util.List;
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
    private String chatname;
}
