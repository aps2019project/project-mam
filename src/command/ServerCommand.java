package command;

import Model.user.User;
import sun.dc.pr.PRError;

public enum  ServerCommand {
    SIGNIN, SIGNUP,
    BUY, SELL, SEARCH, SHOWALL,
    REQUEST_GAME, CREATE_GAME, MOVE, ENDTURN, ATTACK, INSERT,
    ;

    private User user;

    private String message;
    private State state;


    private int baseTurn;
    private String cardName;
    private String cardId;
    private int row;
    private int column;
}
