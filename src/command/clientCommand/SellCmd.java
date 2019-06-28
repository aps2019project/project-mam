package command.clientCommand;

import java.io.DataOutputStream;

public class SellCmd extends ClientCommand {
    private String cardId;

    public SellCmd(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
