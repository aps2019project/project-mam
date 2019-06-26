package view.BattleMenu;

import Controller.GameController.GameController;
import Model.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.pages.Page;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainBattleMenuPage extends Page {

    private static Game game = Game.getInstance();
    public static Game getGame(){
        return game;
    }


    public MainBattleMenuPage() {
        game = Game.getInstance();
        start();
    }

    private static Pane root = new Pane();
    private static GameController controller;

    public static Pane getRoot() {
        return root;
    }

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Game.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();

            controller.init();

            initializeImage();
            controller.initializeGame();

            Scene scene = new Scene(root);
            stage.setScene(scene);
//            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() {
        /*try {
            controller.backGround.setImage(new Image(new FileInputStream("resources/maps/gameBackgaround.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        setBackGround(root, "resources/maps/gameBackgaround.jpg");
        setBackGround(controller.label, "resources/ui/button_primary_middle_glow@2x.png");
    }

    public static GameController getController() {
        return controller;
    }
}
