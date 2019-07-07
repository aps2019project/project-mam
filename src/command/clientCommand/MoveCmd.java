package command.clientCommand;

import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;
import view.pages.Page;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.MOVE;
import static command.CommandType.SELECT;

public class MoveCmd extends ClientCommand {
    private int row;
    private int column;

    public MoveCmd(int row, int column) {
        this.row = row;
        this.column = column;
        type = MOVE;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        handler.getUser().getCurrentGame().getCommands().add(this);
        if(handler.getOppHandler() == null)
            return;
        GsonWriter.sendServerCommand(new ServerCommand(MOVE, row, 8 - column), handler.getOppHandler().getOutput());
        int count = 1;
        for (Map.Entry<ClientHandler, ClientHandler> entry : Server.getGames().entrySet()) {
            if (entry.getKey().getUser().getName().equalsIgnoreCase(handler.getUser().getName()) ||
                    entry.getValue().getUser().getName().equalsIgnoreCase(handler.getUser().getName())){
                for (Map.Entry<String, ClientHandler> handlerEntry : Server.getClients().entrySet()) {
                    if (handlerEntry.getValue().isSeeingLive() && handlerEntry.getValue().getGameNum() == count){
                        GsonWriter.sendServerCommand(new ServerCommand(MOVE, row, column), handlerEntry.getValue().getOutput());
                    }
                }
            }
            count++;
        }
    }
}
