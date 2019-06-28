package view.BattleMenu;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameMoodMenuPage extends Page {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();

    public GameMoodMenuPage(){
        start();
    }

    private static Pane root = new Pane();
    private GameMoodController controller;

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/GameMood.fxml"));
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
        setBackGround(root, "resources/codex/chapter1_background@2x.jpg");
        setBackGround(controller.mood1, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.mood2, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.mood3, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }
}
