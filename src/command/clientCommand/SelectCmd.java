package command.clientCommand;

import command.CommandType;

import java.io.DataOutputStream;

public class SelectCmd extends ClientCommand {
    private int cardId;

    public SelectCmd(int cardId) {
        this.cardId = cardId;
        type = CommandType.SELECT;
    }

    @Override
    public void handleCommand(DataOutputStream output) {

    }
}
