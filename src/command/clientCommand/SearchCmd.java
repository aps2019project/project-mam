package command.clientCommand;

import java.io.DataOutputStream;

public class SearchCmd extends ClientCommand {
    private String cardName;

    public SearchCmd(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
