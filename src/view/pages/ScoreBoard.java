package view.pages;

import Controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScoreBoard extends Page{
    private MainMenuController controller;
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
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() throws FileNotFoundException {

    }

    public MainMenuController getController()
    {
        return controller;
    }

}
