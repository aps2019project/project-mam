package command.clientCommand;

import Model.user.User;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.SCORE_BOARD;

public class ScoreBoardCmd extends ClientCommand {
    public ScoreBoardCmd() {
        type = SCORE_BOARD;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        resetStateOfUsers();
        updateOnlineUsers();
        GsonWriter.sendServerCommand(new ServerCommand(SCORE_BOARD, Result.SUCCESSFUL, User.showUsers()), output);
        GsonWriter.sendServerCommand(new ServerCommand(SCORE_BOARD, Result.SUCCESSFUL, Server.showGames()), output);
    }

    private void resetStateOfUsers() {
        for (User user : User.getUsers())
            user.setOnline(false);
    }

    private void updateOnlineUsers() {
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet())
            entry.getValue().getUser().setOnline(true);
    }
}
