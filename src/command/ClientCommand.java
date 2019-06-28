package command;


import Model.user.User;

public enum ClientCommand {
    SIGNIN, SIGNUP, SAVE,
    BUY, SELL, SEARCH, SHOWALL,
    REQUEST_GAME, CREATE_GAME, MOVE, ENDTURN, ATTACK, INSERT,
    ;

    private User user;

    private String userName;
    private String pass;

    private String cardName;
    private String cardId;
    private int row;
    private int column;

}
