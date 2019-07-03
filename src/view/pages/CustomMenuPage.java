package view.pages;

import Controller.CustomController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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

            controller.init();

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
        setBackGround(root, "resources/codex/generic_background@2x.jpg");
        setBackGround(controller.cardVB, Color.rgb(0, 0, 0, 0.5));
        setBackGround(controller.spellVB, Color.rgb(0, 0, 0, 0.5));
        setBackGround(controller.minionVB, Color.rgb(0, 0, 0, 0.5));
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));
    }
}
