package co.com.ancas.controller;

import co.com.ancas.models.domain.Messages;
import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.request.ChatStatusRequest;
import co.com.ancas.request.MediaMessageRequest;
import co.com.ancas.request.MessageCreationRequest;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.MessageInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.services.MessagesAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/message")
@Tag(name = "Message", description = "Message API")
public class MessageController {
    private final MessagesAppService messagesAppService;

    @PostMapping()
    public ResponseEntity<GeneralResponse<String>> createMessage(
            @Valid @RequestBody MessageCreationRequest messageCreationRequest
    ) {
        messagesAppService.createMessage(messageCreationRequest);
       return  new ResponseEntity<>(
               GeneralResponse.<String>builder()
                       .data(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                       .message(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                       .status(HttpStatus.CREATED.value())
                       .build()
               ,
               HttpStatus.CREATED
       );
    }

    @PostMapping("/files")
    public ResponseEntity<GeneralResponse<String>> createMessageWithFile(
            @Valid @RequestBody MediaMessageRequest mediaMessageRequest
    ) throws IOException {
        messagesAppService.uploadFile(mediaMessageRequest);
        return  new ResponseEntity<>(
                GeneralResponse.<String>builder()
                        .data(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                        .message(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                        .status(HttpStatus.CREATED.value())
                        .build()
                ,
                HttpStatus.CREATED
        );
    }


    @PatchMapping("/seen")
    public ResponseEntity<GeneralResponse<String>> setMessagesToSeen(
            @Valid @RequestBody ChatStatusRequest chatStatusRequest
            ) {
        messagesAppService.setMessagesToSeen(chatStatusRequest);
        return  ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .data(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                        .message(MessagesData.MESSAGE_CREATED_SUCCESSFULY.getMessage())
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }


    @GetMapping("/chat/{chatId}")
    public ResponseEntity<GeneralResponse<PaginationResponse<MessageInformationResponse>>>getAllMessages(
            @PathVariable("chatId") String chatId,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) {
        return ResponseEntity.ok(
                GeneralResponse.<PaginationResponse<MessageInformationResponse>>builder()
                        .data(messagesAppService.findMessagesByChatId(chatId,page,size))
                        .message(MessagesData.CHAT_INFORMATION.getMessage())
                        .status(HttpStatus.OK.value())
                        .build()
        );
    }

}
