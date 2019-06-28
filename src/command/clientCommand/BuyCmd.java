package command.clientCommand;

import java.io.DataOutputStream;

public class BuyCmd extends ClientCommand {
    private String cardName;

    public BuyCmd(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
