package co.com.ancas.postgres.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "chats")
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntity  extends AuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private UserEntity sender;
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserEntity recipient;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER)
    @OrderBy("createdDate DESC")
    private List<MessageEntity> messages;
}
