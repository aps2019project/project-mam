package view.BattleMenu;

import Controller.GameController.GameController;
import Model.game.Game;
import command.clientCommand.EndTurnCmd;
import gson.GsonWriter;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.pages.Page;

import java.awt.*;
import java.io.*;

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


            controller.initializeGame();
            Scene scene = new Scene(root);
            stage.setScene(scene);
//            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
            initializeImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() {
        setBackGround(root, "resources/maps/gameBackgaround.jpg");
        setBackGround(controller.label, "resources/ui/button_primary_middle_glow@2x.png");

        EventHandler<KeyEvent> handler = event -> {
            if (event.getCode()  == KeyCode.Z) {
                if (!game.isMyTurn())
                    return;
                controller.endTurn();
                if (game.isMulti())
                    GsonWriter.sendClientCommand(new EndTurnCmd(), Page.getOutput());
                //controller.endTurn();
            }
        };

        root.getScene().addEventHandler(KeyEvent.KEY_PRESSED, handler);
    }

    public static GameController getController() {
        return controller;
    }
}
