package co.com.ancas.postgres.entities;

import co.com.ancas.models.enums.MessageState;
import co.com.ancas.models.enums.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "messages")
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity  extends AuditingEntity{
    @Id
    @SequenceGenerator(name = "msg_seq", sequenceName = "msg_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "msg_seq")
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    private MessageState state;
    @Enumerated(EnumType.STRING)
    private MessageType type;
    @Column(name = "sender_id", nullable = false)
    private String senderId;
    @Column(name = "recipient_id",nullable = false)
    private String recipientId;
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatEntity chat;


}
