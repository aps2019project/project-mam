package command.clientCommand;

import Model.game.LastGame;
import Model.user.User;
import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import view.pages.Page;

import java.io.DataOutputStream;

public class ReplayCmd extends ClientCommand {
    private int gameId;

    public ReplayCmd(int gameId) {
        this.gameId = gameId;
        type = CommandType.REPLAY;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        try {
            User.user.setCurrentGame(LastGame.setCurrentGame(gameId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        new Thread(() -> {
            int size = User.user.getCurrentGame().getCommands().size();
            int counter = 0;
            while (size > counter) {
                ClientCommand command = User.user.getCurrentGame().getCommands().get(counter++);
                GsonWriter.sendServerCommand(new ServerCommand(command.type, command.getCardId(), command.getRow(), command.getColumn())
                        , clientHandler.getOutput());
            }
        }).start();
    }
}
