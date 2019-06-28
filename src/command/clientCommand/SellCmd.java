package command.clientCommand;

import java.io.DataOutputStream;
import static command.CommandType.*;

public class SellCmd extends ClientCommand {
    private String cardId;

    public SellCmd(String cardId) {
        this.cardId = cardId;
        type = SELL;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
