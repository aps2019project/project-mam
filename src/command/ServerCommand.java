package command;

import Model.user.User;

public class   ServerCommand {

    public ServerCommand(CommandType type) {
        this.type = type;
    }

    public ServerCommand(CommandType type,User user, Result result) {
        this.type = type;
        this.user = user;
        this.result = result;
    }

    public ServerCommand(CommandType type, Result result, String message) {
        this.type = type;
        this.result = result;
        this.message = message;
    }


    public ServerCommand(CommandType type, User user, String message, Result result, int baseTurn, String cardName, String cardId, int row, int column) {
        this.type = type;
        this.user = user;
        this.message = message;
        this.result = result;
        this.baseTurn = baseTurn;
        this.cardName = cardName;
        this.cardId = cardId;
        this.row = row;
        this.column = column;
    }

    private CommandType type;
    private User user;

    private String message;
    private Result result;

    private int baseTurn;
    private String cardName;
    private String cardId;
    private int row;
    private int column;

    public CommandType getType() {
        return type;
    }

    public void setType(CommandType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public int getBaseTurn() {
        return baseTurn;
    }

    public void setBaseTurn(int baseTurn) {
        this.baseTurn = baseTurn;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}