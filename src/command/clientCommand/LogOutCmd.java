package command.clientCommand;

import command.CommandType;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;

import static command.CommandType.LOGOUT;

public class LogOutCmd extends ClientCommand {

    protected String userName;

    public LogOutCmd(String userName) {
        this.userName = userName;
        type = LOGOUT;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler clientHandler) {
        Server.getClients().remove(userName);
    }
}
