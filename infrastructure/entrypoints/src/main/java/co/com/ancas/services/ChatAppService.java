package co.com.ancas.services;

import co.com.ancas.models.domain.ChatCreation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.ChatCreationRequest;
import co.com.ancas.response.ChatCreatedResponse;
import co.com.ancas.response.ChatInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.ports.IChatPort;
import co.com.ancas.util.CurrentUser;
import co.com.ancas.util.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatAppService {
    private final IChatPort chatPort;
    private final CurrentUser currentUser;

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public ChatCreatedResponse createChat(ChatCreationRequest chatCreationRequest) {
        return Mapper.map(chatPort.createChat(Mapper.map(chatCreationRequest, ChatCreation.class)),ChatCreatedResponse.class);
    }

    @Transactional(value = "whatsappTransactionManager",rollbackFor = Exception.class)
    public PaginationResponse<ChatInformationResponse> getChatInformationByReceiverId(Integer page, Integer size) {
        String currentuser = currentUser.getCurrentUserId();
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return Pagination.getPaginationResponse(chatPort.getChatInformationByReceiverId(currentuser,pageable), ChatInformationResponse.class);
    }
}
