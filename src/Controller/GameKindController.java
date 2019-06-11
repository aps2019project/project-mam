package Controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.BattleMenu.GameMoodMenuPage;
import view.BattleMenu.MissionsMenuPage;
import view.pages.Page;


public class GameKindController {

    public Label story;
    public Label custom;

    public ImageView back;

    public void setStory() {
        Page.getPages().push(new MissionsMenuPage());
    }

    public void setCustom() {
        Page.getPages().push(new GameMoodMenuPage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
