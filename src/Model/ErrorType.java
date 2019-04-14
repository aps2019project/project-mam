package Model;

public enum ErrorType {
    DUPLICATE_USERNAME("username is'nt new"),
    INVALID_USERNAME("userName is inValid"),
    INCORRECT_PASSWORD("password is incorrect"),
    ;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorType(String message) {
        this.message = message;
    }


}
