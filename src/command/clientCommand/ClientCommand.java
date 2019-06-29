package command.clientCommand;


import command.CommandType;

import java.io.DataOutputStream;

public abstract class ClientCommand {
    protected CommandType type;

    public void handleCommand(DataOutputStream output){}

    public CommandType getType() {
        return type;
    }

}
