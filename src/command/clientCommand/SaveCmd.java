package command.clientCommand;

import Model.user.User;
import command.clientCommand.ClientCommand;
import gson.GsonWriter;
import server.ClientHandler;

import java.io.DataOutputStream;
import java.io.IOException;

import static command.CommandType.*;

public class SaveCmd extends ClientCommand {
    public SaveCmd() {
        type = SAVE;
    }

    @Override
    public void handleCommand(DataOutputStream output, ClientHandler handler) {
        try {
            GsonWriter.writeUser(User.user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
