package Model;

public enum ErrorType {
    DUPLICATE_USERNAME("username is'nt new"),
    INVALID_USERNAME("userName is inValid"),
    INCORRECT_PASSWORD("password is incorrect"),
    INVALID_COMMAND("command is invalid"),
    INVALID_DECK("selected deck is invalid"),
    INVALID_DECK_2("selected deck for second player is invalid"),


    ;

    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }


}
