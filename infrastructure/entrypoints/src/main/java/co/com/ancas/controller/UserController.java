package co.com.ancas.controller;

import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.services.UserAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/users")
@Tag(name = "User", description = "User operations")
public class UserController {
    private final UserAppService userAppService;

    @GetMapping("/contacts")
    public ResponseEntity<GeneralResponse<List<UserInformationResponse>>> getContacts(){
        return ResponseEntity.ok(
                GeneralResponse.<List<UserInformationResponse>>builder()
                        .data(userAppService.findUsersExceptMe())
                        .message(MessagesData.CONTACTS_FOUND.getMessage())
                        .status(200)
                        .build()
        );
    }
}
