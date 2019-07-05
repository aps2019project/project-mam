package command.clientCommand;

import Model.user.User;
import command.clientCommand.ClientCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;
import java.io.IOException;

import static command.CommandType.*;

public class SaveCmd extends ClientCommand {
    private User user;
    public SaveCmd(User user) {
        this.user = user;
        type = SAVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        try {
            handler.setUser(user);
            GsonWriter.writeUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
