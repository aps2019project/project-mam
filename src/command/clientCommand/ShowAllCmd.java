package command.clientCommand;

import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class ShowAllCmd extends ClientCommand {
    public ShowAllCmd() {
        type = SHOWALL;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        super.handleCommand(output, handler);
    }
}
