package command.clientCommand;

import Model.game.Game;
import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.GET_GAME;

public class GetGameCmd extends ClientCommand {

    private Game game;
    private String userName;

    public GetGameCmd(Game game, String userName) {
        this.game = game;
        this.userName = userName;
        type = GET_GAME;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet()) {
            if (entry.getKey().equalsIgnoreCase(userName)){
                GsonWriter.sendServerCommand(new ServerCommand(GET_GAME, game), entry.getValue().getOutput());
            }
        }
    }
}
