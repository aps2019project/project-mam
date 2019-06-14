package view.pages;

import Controller.CustomController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomMenuPage extends Page {

    public CustomMenuPage(){
        start();
    }

    private static Pane root = new Pane();
    private CustomController controller;



    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Custom.fxml"));
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
        setBackGround(root, "resources/codex/chapter7_background@2x.jpg");
        /*setBackGround(controller.search_lb, "resources/ui/button_primary_middle_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));*/


    }
}
