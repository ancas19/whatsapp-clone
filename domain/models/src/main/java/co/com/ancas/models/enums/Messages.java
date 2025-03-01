package co.com.ancas.models.enums;

public enum Messages {

    //Sucess messages
    CHAT_CREATED("Chat created successfully"),

    //Error messages
    USER_NOT_FOUND("User not found"),
    MESSAGE_ERROR_DATA_INCORRECT("The data entered is incorrect"),
    MESSAGE_GENERAL_NOT_FOUND("The requested resource was not found"),
    MESSAGE_EXCEPTION("An exception occurred, please contact support team"),
    MESSAGE_GENERAL_FORBIDDEN("You do not have permission to access this resource"),;



    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
