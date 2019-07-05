package command.clientCommand;

import Model.user.User;
import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.GET_GAME;
import static command.CommandType.REQUEST_LIVE;

public class RequestLiveCmd extends ClientCommand {
    private int gameNum;

    public RequestLiveCmd(int gameNum) {
        this.gameNum = gameNum;
        type = REQUEST_LIVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        int count = 1;
        for (Map.Entry<ClientHandler, ClientHandler> entry : Server.getGames().entrySet()) {
            if (count++ == gameNum){
                System.out.println("requested");
                GsonWriter.sendServerCommand(new ServerCommand(GET_GAME), entry.getKey().getOutput());
                break;
            }
        }
    }
}
