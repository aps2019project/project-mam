package command.clientCommand;

import Model.user.User;
import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;

import static command.CommandType.ATTACK;

public class AttackCmd extends ClientCommand {
    private String oppId;
    private int row;
    private int column;

    public AttackCmd(String oppId) {
        this.oppId = oppId;
        /*this.row = row;
        this.column = column;*/
        type = ATTACK;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        GsonWriter.sendServerCommand(new ServerCommand(ATTACK, oppId), handler.getOppHandler().getOutput());
    }
}
