package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.ChatEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, String> {
    @Query("SELECT DISTINCT c FROM ChatEntity c WHERE c.sender.id = :senderId OR c.recipient.id = :senderId ORDER BY c.createdDate DESC")
    Page<ChatEntity> findChatBySenderId(@Param("senderId") String senderId, Pageable pageable);
    @Query("SELECT DISTINCT c FROM ChatEntity c WHERE (c.sender.id = :senderId AND c.recipient.id = :recipientId) OR (c.sender.id = :recipientId AND c.recipient.id = :senderId) ORDER BY c.createdDate DESC")
    Optional<ChatEntity> findBySenderIdAndRecipientId(@Param("senderId") String senderId, @Param("recipientId") String recipientId);
}
