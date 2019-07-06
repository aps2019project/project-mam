package command.clientCommand;

import Model.game.LastGame;
import Model.user.User;
import command.CommandType;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.END_GAME;

public class EndGameCmd extends ClientCommand {
    public EndGameCmd() {
        type = END_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        LastGame.getLastGames().add(handler.getUser().getCurrentGame());
        GsonWriter.writeLastGame(handler.getUser().getCurrentGame());
    }
}