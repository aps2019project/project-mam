package Controller;

import Controller.GameController.AudioController;
import Model.game.Game;
import Model.user.AI;
import Model.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameKindMenuPage;
import view.BattleMenu.MultiPlayerMoodPage;
import view.BattleMenu.SelectUserMenuPage;
import view.pages.Page;


public class BattleMenuController {

    public Label single;
    public Label multi;

    public ImageView back;

    public void setSingle() {
        AudioController.getInstance().onSelect();
        BattleMenuPage.setNumOfPlayers("1");
        BattleMenuPage.setSecondUser(AI.getInstance().getAI());
        Page.getPages().push(new GameKindMenuPage());
    }

    public void setMulti() {
        AudioController.getInstance().onSelect();
        BattleMenuPage.setNumOfPlayers("2");
        BattleMenuPage.setGameKind("custom");
        Page.getPages().push(new MultiPlayerMoodPage());
    }

    public void setBack() {
        AudioController.getInstance().onSelect();
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
