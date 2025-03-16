package co.com.ancas.controller;

import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.services.UserAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/users")
@Tag(name = "User", description = "User operations")
public class UserController {
    private final UserAppService userAppService;

    @GetMapping(value="/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeneralResponse<PaginationResponse<UserInformationResponse>>> getContacts(
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity.ok(
                GeneralResponse.<PaginationResponse<UserInformationResponse>>builder()
                        .data(userAppService.findUsersExceptMe(pageable))
                        .message(MessagesData.CONTACTS_FOUND.getMessage())
                        .status(200)
                        .build()
        );
    }
}
