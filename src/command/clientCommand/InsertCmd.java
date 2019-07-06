package command.clientCommand;

import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.INSERT;
import static command.CommandType.MOVE;

public class InsertCmd extends ClientCommand {
    private String cardName;
    private int row;
    private int column;

    public InsertCmd(String cardName, int row, int column) {
        this.cardName = cardName;
        this.row = row;
        this.column = column;
        type = INSERT;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        handler.getUser().getCurrentGame().getCommands().add(this);
        GsonWriter.sendServerCommand(new ServerCommand(INSERT, cardName, row, 8 - column), handler.getOppHandler().getOutput());
        int count = 1;
        for (Map.Entry<ClientHandler, ClientHandler> entry : Server.getGames().entrySet()) {
            if (entry.getKey().getUser().getName().equalsIgnoreCase(handler.getUser().getName()) ||
                    entry.getValue().getUser().getName().equalsIgnoreCase(handler.getUser().getName())){
                for (Map.Entry<String, ClientHandler> handlerEntry : Server.getClients().entrySet()) {
                    if (handlerEntry.getValue().isSeeingLive() && handlerEntry.getValue().getGameNum() == count){
                        GsonWriter.sendServerCommand(new ServerCommand(INSERT, cardName, row, column), handlerEntry.getValue().getOutput());
                    }
                }
            }
            count++;
        }
    }
}
