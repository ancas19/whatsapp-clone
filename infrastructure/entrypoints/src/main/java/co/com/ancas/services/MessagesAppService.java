package co.com.ancas.services;

import co.com.ancas.models.domain.ChatStatus;
import co.com.ancas.models.domain.MediaMessage;
import co.com.ancas.models.domain.MessageCreation;
import co.com.ancas.models.domain.MessageInformation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.ChatStatusRequest;
import co.com.ancas.request.MediaMessageRequest;
import co.com.ancas.request.MessageCreationRequest;
import co.com.ancas.response.MessageInformationResponse;
import co.com.ancas.uses_cases.ports.IMessagesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesAppService {
    private final IMessagesPort messagesPort;


    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public void createMessage(MessageCreationRequest messageCreationRequest){
        messagesPort.createMessage(Mapper.map(messageCreationRequest, MessageCreation.class));
    }

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public List<MessageInformationResponse> findMessagesByChatId(String chatId){
        return Mapper.mapAll(messagesPort.findMessagesByChatId(chatId), MessageInformationResponse.class);
    }

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public void setMessagesToSeen(ChatStatusRequest chatStatus){
        messagesPort.setMessagesToSeen(Mapper.map(chatStatus, ChatStatus.class));
    }

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public void uploadFile(MediaMessageRequest mediaMessage) throws IOException{
        messagesPort.uploadFile(Mapper.map(mediaMessage, MediaMessage.class));
    }
}
