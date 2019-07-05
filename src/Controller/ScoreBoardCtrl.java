package Controller;

import command.clientCommand.ScoreBoardCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import view.pages.Page;

public class ScoreBoardCtrl {

    public ImageView back;
    public ScrollPane scoreBoard;
    public Label scoreBoardLb;
    public ScrollPane games;
    public Label gamesLb;

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void initialize(){
        GsonWriter.sendClientCommand(new ScoreBoardCmd(), Page.getOutput());
        scoreBoardLb.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
        gamesLb.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
    }
}
