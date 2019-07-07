package command.clientCommand;


import Model.user.User;
import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

public abstract class ClientCommand {
    protected CommandType type;

    /*private String cardId;
    private int row;
    private int column;
    private String oppId;*/

    public void handleCommand(DataOutputStream output, ClientHandler clientHandler){}

    public CommandType getType() {
        return type;
    }

    public String getCardId() {
        return null;
    }

    public int getRow() {
        return 0;
    }

    public int getColumn() {
        return 0;
    }
}
