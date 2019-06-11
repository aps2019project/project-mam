package Controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.GameMoodMenuPage;
import view.pages.Page;

public class SelectUserController {

    public Label users;
    public Label select;

    public TextField user;

    public ImageView back;

    public void setSelect() {
        Page.getPages().push(new GameMoodMenuPage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
