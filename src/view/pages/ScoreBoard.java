package view.pages;

import Controller.MainMenuController;
import Controller.ScoreBoardCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScoreBoard extends Page{
    private ScoreBoardCtrl controller;
    private static Pane root = new Pane();

    public ScoreBoard(){

        start();
    }

    public void start(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/ScoreBoard.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            initializeImage();
            controller.initialize();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() throws FileNotFoundException {
        setBackGround(root, "resources/codex/chapter18_background@2x.jpg");
        //setBackGround(controller.scoreBoard, Color.rgb(0, 0, 0, 0.5));
        //setBackGround(controller.scoreBoardLb, Color.rgb(0, 0, 0, 0.5));
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));
    }

}
