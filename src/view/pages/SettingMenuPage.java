package view.pages;

import Controller.HistoryCtrl;
import Controller.SettingCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingMenuPage extends Page {
    private Pane root;
    private SettingCtrl controller;

    public SettingMenuPage(){
        start();
    }

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Setting.fxml"));
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
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));
    }
}
