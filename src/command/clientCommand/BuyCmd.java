package command.clientCommand;

import java.io.DataOutputStream;
import static command.CommandType.*;

public class BuyCmd extends ClientCommand {
    private String cardName;

    public BuyCmd(String cardName) {
        this.cardName = cardName;
        type = BUY;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
