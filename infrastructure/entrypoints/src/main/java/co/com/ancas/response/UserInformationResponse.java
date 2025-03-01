package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInformationResponse implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;
    boolean online;
}
