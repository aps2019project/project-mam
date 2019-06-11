package Controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.pages.Page;

public class MissionController {

    public Label mission1;
    public Label mission2;
    public Label mission3;

    public ImageView back;

    public void setMission1() {
    }

    public void setMission2() {
    }

    public void setMission3() {
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
