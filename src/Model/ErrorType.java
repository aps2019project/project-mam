package Model;

public enum ErrorType {
    DUPLICATE_USERNAME("username is'nt new"),
    INVALID_USERNAME("userName is inValid"),
    INCORRECT_PASSWORD("password is incorrect"),
    INVALID_COMMAND("command is invalid"),
    INVALID_DECK("selected deck is invalid"),
    INVALID_DECK_2("selected deck for second player is invalid"),
    UNAVAILABLE_CARD_OR_ITEM("card or item is'nt available"),
    MONEY_IS_NOT_ENOUGH("you have'nt enough money for buy this card or item"),
    THREE_ITEM("you have 3 item, you can'nt buy more item"),
    SUCCESSFUL_BUY("buy successful"),
    NOT_FOUND_CARD_OR_ITEM("card or item not found"),
    FOUND_CARD("card found:\n"),
    FOUND_ITEM("item found:\n"),

    ;

    private String message;

    public String getMessage() {
        return message;
    }

    ErrorType(String message) {
        this.message = message;
    }


}
