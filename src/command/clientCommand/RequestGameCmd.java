package command.clientCommand;

import command.Result;
import command.ServerCommand;
import gson.GsonReader;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

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
        handler.setRequested(true);
        for (Map.Entry<String, ClientHandler> opp : Server.getClients().entrySet()) {
            if (opp.getValue().isRequested() && !opp.getKey().equals(handler.getUser().getName()))
            {
                Server.getGames().put(handler, opp.getValue());
                handler.setRequested(false);
                opp.getValue().setRequested(false);
                GsonWriter.sendServerCommand(new ServerCommand(CREATE_GAME,
                        opp.getValue().getUser(), flag, gameMode, Result.SUCCESSFUL, 1), output);
                handler.setOppHandler(opp.getValue());
                opp.getValue().setOppHandler(handler);
                GsonWriter.sendServerCommand(new ServerCommand(CREATE_GAME,
                        handler.getUser(), flag, gameMode, Result.SUCCESSFUL, 0), opp.getValue().getOutput());
                return;
            }
        }
        GsonWriter.sendServerCommand(new ServerCommand(CREATE_GAME, Result.FAILED), output);
    }
}
