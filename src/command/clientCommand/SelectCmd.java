package command.clientCommand;

import Model.user.User;
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
        User.user.getCurrentGame().getCommands().add(this);
        if (User.user.getCurrentGame().getGame().isMulti())
            GsonWriter.sendServerCommand(new ServerCommand(SELECT, cardId), handler.getOppHandler().getOutput());
    }
}
