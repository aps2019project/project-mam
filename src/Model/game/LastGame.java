package Model.game;

import command.clientCommand.ClientCommand;

import java.util.ArrayList;

public class LastGame {
    private Game game;
    private String name;
    private ArrayList<ClientCommand> commands;

    public LastGame(Game game) {
        this.game = game;
    }

    public LastGame(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    public ArrayList<ClientCommand> getCommands() {
        return commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCommands(ArrayList<ClientCommand> commands) {
        this.commands = commands;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
