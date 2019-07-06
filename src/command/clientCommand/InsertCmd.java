package command.clientCommand;

import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.INSERT;

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
    }
}
