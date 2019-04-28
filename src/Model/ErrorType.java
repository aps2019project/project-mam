package Model;

public enum ErrorType {
    DUPLICATE_USERNAME("username is'nt new"),
    DUPLICATE_DECK("deck name is'nt new"),
    INVALID_USERNAME("userName is inValid"),
    INCORRECT_PASSWORD("password is incorrect"),
    INVALID_COMMAND("command is invalid"),
    VALID_DECK("selected deck is valid"),
    INVALID_DECK("selected deck is invalid"),
    INVALID_DECK_2("selected deck for second player is invalid"),
    UNAVAILABLE_CARD_OR_ITEM("card or item is'nt available"),
    MONEY_IS_NOT_ENOUGH("you have'nt enough money for buy this card or item"),
    THREE_ITEM("you have 3 item\nyou can't buy more item"),
    SUCCESSFUL_BUY("buy successful"),
    SUCCESSFUL_SELL("sell successful"),
    NOT_FOUND_CARD_OR_ITEM("card or item not found"),
    NOT_FOUND_CARD_OR_ITEM_IN_COLLECTION("card or item is'nt in collection"),
    NOT_FOUND_DECK("deck not found"),
    FOUND_CARD("card found:"),
    FOUND_ITEM("item found:"),
    TWENTY_CARD("you have 20 card in your deck\n  you can't add more card"),
    EXTRA_HERO("you have a hero\n  you can't add more hero"),
    EXTRA_USABLEITEM("you have a item\n  you can't add more item"),
    REPETITIVE_CARD("this card is added before"),
    INVALID_MOOD_NUM("invalid mood number"),
    SUCCESSFUL_ADDING_CARD("add card successful"),
    SUCCESSFUL_ADDING_ITEM("add item successful"),
    SUCCESSFUL_ADDING_HERO("add hero successful"),
    SUCCESSFUL_MOVING_CARD(""),
    SELECT_MAIN_DECK("main deck selected"),
    NOT_SELECT_MAIN_DECK("main deck did'nt select"),
    INVALID_TARGET("target is invalid"),


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
