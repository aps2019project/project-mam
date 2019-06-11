package Controller;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.pages.Page;

public class GameMoodController {

    public Label mood1;
    public Label mood2;
    public Label mood3;

    public TextField numOfFlags;

    public ImageView back;

    public void setMood1() {

    }

    public void setMood2() {

    }

    public void setMood3() {

    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
