package command;


import Model.user.User;

public class ClientCommand {

    public ClientCommand(CommandType type){
        this.type = type;
    }

    public ClientCommand(CommandType type, String userName, String pass) {
        this.type = type;
        this.userName = userName;
        this.pass = pass;
    }

    private CommandType type;
    private User user;

    private String userName;
    private String pass;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
