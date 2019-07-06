package command.clientCommand;

import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

public class EndTurnCmd extends ClientCommand {

    public EndTurnCmd() {
        type = CommandType.ENDTURN;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        handler.getUser().getCurrentGame().getCommands().add(this);
        GsonWriter.sendServerCommand(new ServerCommand(CommandType.ENDTURN), handler.getOppHandler().getOutput());
    }
}
