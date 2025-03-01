package co.com.ancas.models.enums;

public enum Constants {

    ATTACHMENT("attachment");
    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
