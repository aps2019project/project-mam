package Model.game;

import Model.user.User;
import command.clientCommand.ClientCommand;
import command.clientCommand.SaveCmd;
import gson.GsonWriter;
import view.pages.Page;

import java.util.ArrayList;

public class LastGame {
    private Game game;
    private String name;
    private int id;
    private ArrayList<ClientCommand> commands = new ArrayList<>();
    private static ArrayList<LastGame> lastGames = new ArrayList<>();

    public LastGame(Game game) {
        this.game = game;
        this.id = game.getId();
    }

    public LastGame(Game game, String name) {
        this.game = game;
        this.name = name;
    }

    public LastGame() {
    }

    public static ArrayList<LastGame> getLastGames() {
        return lastGames;
    }

    public ArrayList<ClientCommand> getCommands() {
        return commands;
    }

    public int getId() {
        return id;
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

    public void addGameToLastGames(Game game) {
        if (lastGames.size() == 1)
            lastGames.remove(lastGames.size() - 1);
        LastGame lastGame = new LastGame(game, "" + lastGames.size());
        lastGames.add(lastGame);
//        currentGame = lastGame;
        GsonWriter.sendClientCommand(new SaveCmd(User.user), Page.getOutput());
    }
}
