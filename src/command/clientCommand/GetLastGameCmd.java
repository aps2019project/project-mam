package command.clientCommand;

import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.GET_LAST_GAME;

public class GetLastGameCmd extends ClientCommand {
    private int id;

    public GetLastGameCmd(int id) {
        this.id = id;
        type = GET_LAST_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        super.handleCommand(output, clientHandler);
    }
}
