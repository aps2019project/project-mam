package Controller;

import Controller.GameController.GameController;
import Model.game.Game;
import Model.game.LastGame;
import Model.user.User;
import gson.GsonReader;
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
        /*for (LastGame lastGame : User.user.getLastGames()) {
            games.append(counter++).append(". ").append(lastGame.getName()).append("_    ").append(lastGame.getGame().getFirstUser().getName()).
                    append(" vs ").append(lastGame.getGame().getSecondUser().getName()).append("\n");
            history.setText(games.toString());
        }*/
    }

    @FXML
    public void onStartClicked(){
        String gameId = txt.getText();
        new Game(GsonReader.readGame(gameId), false);
        //new Game(User.user.getLastGames().get(gameNumber - 1).getGame());
        //User.user.setCurrentGame(User.user.getLastGames().get(gameNumber - 1));
        Page.getPages().push(new MainBattleMenuPage());
    }
}
