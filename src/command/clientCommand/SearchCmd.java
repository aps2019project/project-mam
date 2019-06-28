package command.clientCommand;

import java.io.DataOutputStream;
import static command.CommandType.*;

public class SearchCmd extends ClientCommand {
    private String cardName;

    public SearchCmd(String cardName) {
        this.cardName = cardName;
        type = SEARCH;
    }

    @Override
    public void handleCommand(DataOutputStream output) {
        super.handleCommand(output);
    }
}
