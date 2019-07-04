package Controller;

import Controller.GameController.AudioController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.SelectUserMenuPage;
import view.pages.Page;

public class MultiPlayerMoodCtrl {

    public Label mood1;
    public Label mood2;
    public Label mood3;

    public TextField numOfFlags;

    public ImageView back;

    @FXML
    public void setMood1() {
        AudioController.getInstance().onSelect();
        BattleMenuPage.setGameMood("1");
        Page.getPages().push(new SelectUserMenuPage());
    }

    @FXML
    public void setMood2() {
        AudioController.getInstance().onSelect();
        BattleMenuPage.setGameMood("2");
        BattleMenuPage.setFlags(1);
        Page.getPages().push(new SelectUserMenuPage());
    }

    @FXML
    public void setMood3() {
        AudioController.getInstance().onSelect();
        BattleMenuPage.setGameMood("3");
        BattleMenuPage.setFlags(Integer.parseInt(numOfFlags.getText()));
        Page.getPages().push(new SelectUserMenuPage());
    }

    @FXML
    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
