package command.clientCommand;

import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

public class EndTurnCmd extends ClientCommand {

    public EndTurnCmd() {
        type = CommandType.ENDTURN;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {

    }
}
