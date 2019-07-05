package command.clientCommand;

import server.ClientHandler;

import java.io.DataOutputStream;

public class GetGameCmd extends ClientCommand {
    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        super.handleCommand(output, clientHandler);
    }
}
