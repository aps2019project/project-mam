package Controller;

import Controller.GameController.GameController;
import Model.game.Game;
import Model.game.LastGame;
import Model.user.User;
import command.clientCommand.ReplayCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.BattleMenu.MainBattleMenuPage;
import view.pages.Page;

public class HistoryCtrl {
    public Label history;
    public Button start;
    public TextField txt;

    public void init(){
        StringBuilder games = new StringBuilder();
        int counter = 1;
        for (Integer game : User.user.getGames()) {
            games.append(counter++).append(":  id: ").append(game).append("\n");
        }
        history.setText(games.toString());
    }

    @FXML
    public void onStartClicked(){
        String gameId = txt.getText();
        new Game(GsonReader.readGame(gameId), true);
        GsonWriter.sendClientCommand(new ReplayCmd(Integer.parseInt(gameId)), Page.getOutput());
        Page.getPages().push(new MainBattleMenuPage());
    }
}
