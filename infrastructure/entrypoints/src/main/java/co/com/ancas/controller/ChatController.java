package co.com.ancas.controller;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.ChatCreationRequest;
import co.com.ancas.response.ChatCreatedResponse;
import co.com.ancas.response.ChatInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.services.ChatAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/chats")
@Tag(name = "Chat", description = "Chat operations")
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

    @GetMapping()
    public ResponseEntity<GeneralResponse<List<ChatInformationResponse>>> getChatsByReceiverId() {
        return new ResponseEntity<GeneralResponse<List<ChatInformationResponse>>>(
                GeneralResponse.<List<ChatInformationResponse>>builder()
                        .data(chatAppService.getChatInformationByReceiverId())
                        .message(Messages.CHAT_INFORMATION.getMessage())
                        .status(HttpStatus.OK.value())
                        .build(),
                HttpStatus.OK
        );
    }

}

