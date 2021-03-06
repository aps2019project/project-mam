package view.pages;

import Controller.GameController.AudioController;
import Controller.MainMenuController;
import Model.user.User;
import gson.GsonReader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainMenuPage extends Page {
    private MainMenuController controller;
    private static MainMenuPage instance;
    private static Pane root = new Pane();

    public MainMenuPage(){
        /*try {
            User.user.getCollection().getDecks().add(GsonReader.getDeck("mission_2"));
            User.user.getCollection().setMainDeck(GsonReader.getDeck("mission_2"));
            User.user.setMainDeck(GsonReader.getDeck("mission_2"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        start();
    }

    public void start(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/MainMenu.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            controller.setUpMusic();
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
        setBackGround(root, "resources/codex/chapter22_background@2x.jpg");
        setBackGround(controller.battleLbl, "resources/ui/button_primary_right.png");
        setBackGround(controller.shopLbl, "resources/ui/button_primary_right.png");
        setBackGround(controller.collectionLbl, "resources/ui/button_primary_right.png");
        setBackGround(controller.saveLbl, "resources/ui/button_primary_right.png");
        setBackGround(controller.customLbl, "resources/ui/button_primary_right.png");
        setBackGround(controller.scoreBoard, "resources/ui/button_primary_right.png");
        controller.exit.setImage(new Image(new FileInputStream("resources/ui/utility_menu/settings.png")));
        controller.logout.setImage(new Image(new FileInputStream("resources/ui/button_close.png")));
    }

    public MainMenuController getController()
    {
        return controller;
    }

    public static MainMenuPage getInstance()
    {
        if (instance == null)
            instance = new MainMenuPage();
        return instance;
    }



}
