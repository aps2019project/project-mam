package command.clientCommand;


import Model.user.User;
import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

public abstract class ClientCommand {
    protected CommandType type;

    public void handleCommand(DataOutputStream output, ClientHandler clientHandler){}

    public CommandType getType() {
        return type;
    }

}
