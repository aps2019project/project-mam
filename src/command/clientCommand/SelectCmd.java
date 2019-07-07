package command.clientCommand;

import Model.user.User;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.SELECT;

public class SelectCmd extends ClientCommand {
    private String cardId;

    public SelectCmd(String cardId) {
        this.cardId = cardId;
        type = SELECT;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        handler.getUser().getCurrentGame().getCommands().add(this);
        /*if (handler.getUser().getCurrentGame().getGame().isMulti())
            GsonWriter.sendServerCommand(new ServerCommand(SELECT, cardId), handler.getOppHandler().getOutput());*/
        if(handler.getOppHandler() == null)
            return;
        GsonWriter.sendServerCommand(new ServerCommand(SELECT, cardId), handler.getOppHandler().getOutput());
        int count = 1;
        for (Map.Entry<ClientHandler, ClientHandler> entry : Server.getGames().entrySet()) {
            if (entry.getKey().getUser().getName().equalsIgnoreCase(handler.getUser().getName()) ||
                    entry.getValue().getUser().getName().equalsIgnoreCase(handler.getUser().getName())){
                for (Map.Entry<String, ClientHandler> handlerEntry : Server.getClients().entrySet()) {
                    if (handlerEntry.getValue().isSeeingLive() && handlerEntry.getValue().getGameNum() == count){
                        GsonWriter.sendServerCommand(new ServerCommand(SELECT, cardId), handlerEntry.getValue().getOutput());
                    }
                }
            }
            count++;
        }
    }
}
