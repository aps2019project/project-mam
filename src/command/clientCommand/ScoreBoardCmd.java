package command.clientCommand;

import Model.user.User;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.SCORE_BOARD;

public class ScoreBoardCmd extends ClientCommand {
    public ScoreBoardCmd() {
        type = SCORE_BOARD;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        GsonWriter.sendServerCommand(new ServerCommand(SCORE_BOARD, Result.SUCCESSFUL, User.showUsers()), output);
    }
}
