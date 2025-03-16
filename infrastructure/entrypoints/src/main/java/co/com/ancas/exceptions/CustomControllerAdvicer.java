package co.com.ancas.exceptions;

import co.com.ancas.models.enums.MessagesData;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.response.ErrorResponse;
import co.com.ancas.response.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomControllerAdvicer {
    private static final Logger log = LoggerFactory.getLogger(CustomControllerAdvicer.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse<Map<String,String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Bad Request Exception: {}", ex.getMessage(),ex);
        Map<String,String> errors= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName= ((FieldError) error).getField();
            String message=error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(
                        GeneralResponse.<Map<String,String>>builder()
                                .message(MessagesData.MESSAGE_ERROR_DATA_INCORRECT.getMessage())
                                .data(errors)
                                .build()
                );
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("Not Found Exception: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_GENERAL_NOT_FOUND.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        log.error("Method Not Supported: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_GENERAL_NOT_FOUND.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, WebRequest request) {
        log.error("Internal Authentication Service Exception: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_GENERAL_FORBIDDEN.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleAuthorizationDeniedException(AuthorizationDeniedException ex, WebRequest request) {
        log.error("Authorization Denied Exception: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_GENERAL_FORBIDDEN.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        log.error("No Resource Found Exception: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_GENERAL_NOT_FOUND.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(ex.getMessage())
                                                .build()
                                )
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse<ErrorResponse>> handelGeneralException(Exception ex, WebRequest request) {
        log.error("Internal Server Exception: {}", ex.getMessage(),ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                body(
                        GeneralResponse.<ErrorResponse>builder()
                                .message(MessagesData.MESSAGE_EXCEPTION.getMessage())
                                .data(
                                        ErrorResponse.builder()
                                                .timeStamp(LocalDate.now())
                                                .details(request.getDescription(false))
                                                .message(MessagesData.MESSAGE_EXCEPTION.getMessage())
                                                .build()
                                )
                                .build()
                );
    }
}
