package co.com.ancas.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GeneralResponse <T>{
    private T data;
    private String message;
    private int status;
}
