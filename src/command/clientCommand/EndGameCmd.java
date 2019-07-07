package command.clientCommand;

import Model.game.LastGame;
import Model.user.User;
import command.CommandType;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;
import java.io.IOException;

import static command.CommandType.END_GAME;

public class EndGameCmd extends ClientCommand {

    private int gameId;

    public EndGameCmd(int gameId) {
        this.gameId = gameId;
        type = END_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        handler.getUser().getCurrentGame().setId(gameId);
        handler.getUser().getGames().add(gameId);
        LastGame.getLastGames().add(handler.getUser().getCurrentGame());
        GsonWriter.writeLastGame(handler.getUser().getCurrentGame());
        try {
            GsonWriter.writeUser(handler.getUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
