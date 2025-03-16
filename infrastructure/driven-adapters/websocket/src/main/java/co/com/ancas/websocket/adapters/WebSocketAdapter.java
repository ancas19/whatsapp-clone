package co.com.ancas.websocket.adapters;

import co.com.ancas.models.domain.Notification;
import co.com.ancas.models.ports.IWebSocketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebSocketAdapter  implements IWebSocketRepositoryPort {
    private static final Logger log= LoggerFactory.getLogger(WebSocketAdapter.class);
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendNotifcation(String userId, Notification notification) {
        log.info("Sending notification to user: {} with payload {}", userId, notification);
        simpMessagingTemplate.convertAndSendToUser(userId,"/chat", notification);
    }
}
