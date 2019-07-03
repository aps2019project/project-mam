package command.clientCommand;

import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import view.pages.Page;

import java.io.DataOutputStream;

import static command.CommandType.MOVE;

public class MoveCmd extends ClientCommand {
    private int row;
    private int column;

    public MoveCmd(int row, int column) {
        this.row = row;
        this.column = column;
        type = MOVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(MOVE, row, 8 - column), handler.getOppHandler().getOutput());
    }
}
