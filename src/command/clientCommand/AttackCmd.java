package command.clientCommand;

import Model.user.User;
import command.CommandType;

import java.io.DataOutputStream;

public class AttackCmd extends ClientCommand {
    private String oppId;
    private int row;
    private int column;

    public AttackCmd(String oppId, int row, int column) {
        this.oppId = oppId;
        this.row = row;
        this.column = column;
        type = CommandType.ATTACK;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
