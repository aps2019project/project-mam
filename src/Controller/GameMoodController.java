package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.pages.Page;

public class GameMoodController {

    public Label mood1;
    public Label mood2;
    public Label mood3;

    public TextField numOfFlags;

    public ImageView back;

    @FXML
    public void setMood1() {
        BattleMenuPage.setGameMood("1");
        BattleMenuPage.createGame();
    }

    @FXML
    public void setMood2() {
        BattleMenuPage.setGameMood("2");
        BattleMenuPage.createGame();
    }

    @FXML
    public void setMood3() {
        BattleMenuPage.setGameMood("3");
        BattleMenuPage.createGame();
    }

    @FXML
    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }


}
