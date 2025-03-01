package co.com.ancas.controller;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.ChatCreationRequest;
import co.com.ancas.response.ChatCreatedResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.services.ChatAppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/chats")
public class ChatController {
    private final ChatAppService chatAppService;

    @PostMapping()
    public ResponseEntity<GeneralResponse<ChatCreatedResponse>> createChat(
            @Valid @RequestBody ChatCreationRequest chatCreationRequest
    ) {
        return new ResponseEntity<GeneralResponse<ChatCreatedResponse>>(
                GeneralResponse.<ChatCreatedResponse>builder()
                        .data(chatAppService.createChat(chatCreationRequest))
                        .message(Messages.CHAT_CREATED.getMessage())
                        .status(HttpStatus.CREATED.value())
                        .build(),
                HttpStatus.CREATED
        );
    }

}

