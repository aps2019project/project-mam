package command.clientCommand;

import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.INSERT;

public class EndTurnCmd extends ClientCommand {

    public EndTurnCmd() {
        type = CommandType.ENDTURN;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(CommandType.ENDTURN), handler.getOppHandler().getOutput());
        int count = 1;
        for (Map.Entry<ClientHandler, ClientHandler> entry : Server.getGames().entrySet()) {
            if (entry.getKey().getUser().getName().equalsIgnoreCase(handler.getUser().getName()) ||
                    entry.getValue().getUser().getName().equalsIgnoreCase(handler.getUser().getName())){
                for (Map.Entry<String, ClientHandler> handlerEntry : Server.getClients().entrySet()) {
                    if (handlerEntry.getValue().isSeeingLive() && handlerEntry.getValue().getGameNum() == count){
                        GsonWriter.sendServerCommand(new ServerCommand(CommandType.ENDTURN), handlerEntry.getValue().getOutput());
                    }
                }
            }
            count++;
        }
    }
}
