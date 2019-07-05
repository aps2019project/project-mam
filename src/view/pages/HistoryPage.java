package view.pages;

import Controller.HistoryCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HistoryPage extends Page{
    private Pane root;
    private HistoryCtrl controller;
    public HistoryPage(){
        start();
    }

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/History.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            initializeImage();
            controller.init();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
        } catch (IOException e) {

        }
    }

    private void initializeImage() throws FileNotFoundException {
        setBackGround(root, "resources/codex/chapter22_background@2x.jpg");
    }
}
