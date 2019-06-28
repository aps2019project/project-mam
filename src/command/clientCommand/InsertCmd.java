package command.clientCommand;

import command.CommandType;

import java.io.DataOutputStream;

public class InsertCmd extends ClientCommand {
    private String cardName;
    private int row;
    private int column;

    public InsertCmd(String cardName, int row, int column) {
        this.cardName = cardName;
        this.row = row;
        this.column = column;
        type = CommandType.INSERT;
    }

    @Override
    public void handleCommand(DataOutputStream output) {

    }
}
