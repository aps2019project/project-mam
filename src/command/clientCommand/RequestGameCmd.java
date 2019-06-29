package command.clientCommand;

import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class RequestGameCmd extends ClientCommand {
    private String gameMode;
    private int flag = 0;

    public RequestGameCmd(String gameMode, int flag) {
        this.gameMode = gameMode;
        this.flag = flag;
        type = REQUEST_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {

    }
}
