package command.clientCommand;

import command.CommandType;

public class RequestGameCmd extends ClientCommand {
    private String gameMode;
    private int flag = 0;

    public RequestGameCmd(String gameMode, int flag) {
        this.gameMode = gameMode;
        this.flag = flag;
        type = CommandType.REQUEST_GAME;
    }

}
