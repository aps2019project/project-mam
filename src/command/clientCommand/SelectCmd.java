package command.clientCommand;

import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.SELECT;

public class SelectCmd extends ClientCommand {
    private String cardId;

    public SelectCmd(String cardId) {
        this.cardId = cardId;
        type = SELECT;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(SELECT, cardId), handler.getOppHandler().getOutput());
    }
}
