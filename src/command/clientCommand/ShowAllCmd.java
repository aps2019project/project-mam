package command.clientCommand;

import Model.shop.Shop;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.*;

public class ShowAllCmd extends ClientCommand {
    public ShowAllCmd() {
        type = SHOWALL;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(SHOWALL, Result.SUCCESSFUL, Shop.getSHOP().show()) , output);
    }
}
