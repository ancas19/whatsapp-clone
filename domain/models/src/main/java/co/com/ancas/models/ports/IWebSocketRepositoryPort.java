package co.com.ancas.models.ports;

import co.com.ancas.models.domain.Notification;

public interface IWebSocketRepositoryPort {
    void sendNotifcation(String userId, Notification notification);
}
