package command.clientCommand;

import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

public class MoveCmd extends ClientCommand {
    private int row;
    private int column;

    public MoveCmd(int row, int column) {
        this.row = row;
        this.column = column;
        type = CommandType.MOVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
    }
}
