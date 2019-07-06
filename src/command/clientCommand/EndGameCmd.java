package command.clientCommand;

import command.CommandType;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.END_GAME;

public class EndGameCmd extends ClientCommand {
    public EndGameCmd() {
        type = END_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        //write user's last game.(with id)
    }
}
