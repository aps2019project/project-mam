package command.clientCommand;

import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.EXIT_GAME;


public class ExitGameCmd extends ClientCommand{
    public ExitGameCmd() {
        type = EXIT_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        clientHandler.setRequested(false);
        GsonWriter.sendServerCommand(new ServerCommand(EXIT_GAME, Result.SUCCESSFUL), output);

    }
}
