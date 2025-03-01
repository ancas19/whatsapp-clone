package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {
    @Query("SELECT DISTINCT c FROM ChatEntity c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY c.createdDate DESC")
    List<ChatEntity>findChatBySenderId(@Param("senderId") Long senderId);
    @Query("SELECT DISTINCT c FROM ChatEntity c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId) ORDER BY c.createdDate DESC")
    List<ChatEntity> findBySenderIdAndRecipientId(@Param("senderId") Long senderId,@Param("recipientId") Long recipientId);
}
