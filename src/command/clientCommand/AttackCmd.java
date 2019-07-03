package command.clientCommand;

import Model.user.User;
import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

public class AttackCmd extends ClientCommand {
    private String oppId;

    public AttackCmd(String oppId) {
        this.oppId = oppId;
        type = CommandType.ATTACK;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(CommandType.ATTACK, oppId), handler.getOppHandler().getOutput());
    }
}
