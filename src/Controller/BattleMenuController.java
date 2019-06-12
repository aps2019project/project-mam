package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import view.BattleMenu.GameKindMenuPage;
import view.BattleMenu.SelectUserMenuPage;
import view.pages.Page;


public class BattleMenuController {

    public Label single;
    public Label multi;

    public ImageView back;

    public void setSingle() {
        Page.getPages().push(new GameKindMenuPage());
    }

    public void setMulti() {
        Page.getPages().push(new SelectUserMenuPage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
