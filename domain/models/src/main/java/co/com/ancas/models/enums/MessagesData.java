package co.com.ancas.models.enums;

public enum MessagesData {

    //Sucess messages
    CHAT_CREATED("Chat created successfully"),
    CHAT_INFORMATION("Chats information obtained successfully"),

    //Error messages,
    USER_NOT_FOUND("User not found"),
    MESSAGE_ERROR_DATA_INCORRECT("The data entered is incorrect"),
    MESSAGE_GENERAL_NOT_FOUND("The requested resource was not found"),
    MESSAGE_EXCEPTION("An exception occurred, please contact support team"),
    MESSAGE_GENERAL_FORBIDDEN("You do not have permission to access this resource"),
    CHAT_NOT_FOUND("Chat not found"),
    MESSAGE_CREATED_SUCCESSFULY("Message created successfully"),
    CONTACTS_FOUND("Contacts retrieved successfully");



    private final String message;

    MessagesData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
