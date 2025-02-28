package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity  extends AuditingEntity{
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;
    @OneToMany(mappedBy = "sender")
    private List<ChatEntity> chatsAsSender;
    @OneToMany(mappedBy = "recipient")
    private List<ChatEntity> chatsAsRecipient;
    @Transient
    public boolean isUserOnline(){
        return Objects.isNull(lastSeen) && lastSeen.isAfter(LocalDateTime.now().plusMinutes(2));
    }
}
