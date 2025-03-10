package co.com.ancas.beans;

import co.com.ancas.models.ports.*;
import co.com.ancas.uses_cases.adapters.ChatAdapter;
import co.com.ancas.uses_cases.adapters.MessagesAdapter;
import co.com.ancas.uses_cases.adapters.UserAdapter;
import co.com.ancas.uses_cases.ports.IChatPort;
import co.com.ancas.uses_cases.ports.IMessagesPort;
import co.com.ancas.uses_cases.ports.IUserPorts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

    @Bean
    public IUserPorts userPorts(IUserRepositoryPort userRepositoryPort){
        return new UserAdapter(userRepositoryPort);
    }

    @Bean
    public IChatPort chatPort(IChatRepositoryPort chatRepositoryPort, IUserPorts userPorts){
        return new ChatAdapter(chatRepositoryPort, userPorts);
    }

    @Bean
    public IMessagesPort messagesPort(IMessageRepositoryPort messageRepositoryPort, ICloudinaryPort cloudinaryPort, IUserPorts userPorts, IChatPort chatPorts, IWebSocketRepositoryPort webSocketRepositoryPort){
        return new MessagesAdapter(webSocketRepositoryPort,messageRepositoryPort, cloudinaryPort, userPorts, chatPorts);
    }
}
