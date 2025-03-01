package co.com.ancas.postgres.repositories;

import co.com.ancas.models.enums.MessageState;
import co.com.ancas.postgres.entities.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessageEntity, Long> {

    @Query("SELECT m FROM MessageEntity m WHERE m.chat.id = :chatId ORDER BY m.createdDate DESC")
    List<MessageEntity> findByChatId(@Param("chatId") String chatId);
    @Modifying
    @Query("UPDATE MessageEntity m SET m.state =:newState WHERE m.chat.id = :chatId")
    void setMessagesAsReadByChat(@Param("chatId") String chatId, @Param("newState") MessageState newState);
}
