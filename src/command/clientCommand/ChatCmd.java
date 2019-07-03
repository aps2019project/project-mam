package command.clientCommand;

import command.CommandType;
import command.ServerCommand;
import gson.GsonWriter;
import server.ClientHandler;
import server.Server;

import java.io.DataOutputStream;
import java.util.Map;

import static command.CommandType.CHAT;

public class ChatCmd extends ClientCommand {
    private String message;

    public ChatCmd(String message) {
        this.message = message;
        type = CHAT;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet()) {
            if (!entry.getKey().equals(handler.getUser().getName())){
                GsonWriter.sendServerCommand(new ServerCommand(CHAT, handler.getUser().getName(), message), entry.getValue().getOutput());
            }
        }
    }
}
