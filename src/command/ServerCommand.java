package command;

import Model.card.Card;
import Model.game.Game;
import Model.item.Item;
import Model.user.User;

public class ServerCommand {
    private CommandType type;
    private User user;
    private Card card;
    private String userName;

    private String message;
    private Result result;

    private Game game;
    private String gameMode;
    private int flag;
    private int baseTurn;
    private String cardName;
    private String cardId;
    private int row;
    private int column;

    public ServerCommand(CommandType type) {
        this.type = type;
    }

    public ServerCommand(CommandType type, User user, Result result) {
        this(type);
        this.user = user;
        this.result = result;
    }

    public ServerCommand(CommandType type, Result result, String message) {
        this(type);
        this.result = result;
        this.message = message;
    }


    public ServerCommand(CommandType type, User user, String message, Result result, int baseTurn, String cardName, String cardId, int row, int column) {
        this(type, user, result);
        this.message = message;
        this.baseTurn = baseTurn;
        this.cardName = cardName;
        this.cardId = cardId;
        this.row = row;
        this.column = column;
    }

    public ServerCommand(CommandType type, Result result) {
        this.type = type;
        this.result = result;
    }

    public ServerCommand(CommandType type, User user, Result result, String message) {
        this(type, result, message);
        this.user = user;
    }

    public ServerCommand(CommandType type, int row, int column){
        this.type = type;
        this.row = row;
        this.column = column;
    }

    public ServerCommand(CommandType type, String cardId) {
        this.type = type;
        this.cardId = cardId;
    }

    public ServerCommand(CommandType type, User user,int flag, String gameMode, Result result, int baseTurn) {
        this(type, user, result);
        this.baseTurn = baseTurn;
        this.flag = flag;
        this.gameMode = gameMode;
    }

    public ServerCommand(CommandType type, String cardName, int row, int column) {
        this(type, row, column);
        this.cardName = cardName;
    }

    public ServerCommand(CommandType type, Card card){
        this.type = type;
        this.card = card;
    }

    public ServerCommand(CommandType type, String userName, String message) {
        this.type = type;
        this.userName = userName;
        this.message = message;
    }

    public ServerCommand(CommandType type, Game game) {
        this.type = type;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
