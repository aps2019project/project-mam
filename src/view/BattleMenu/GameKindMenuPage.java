package view.BattleMenu;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameKindMenuPage extends Page {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();


    private static Pane root = new Pane();
    private GameKindController controller;

    public GameKindMenuPage(){
        start();
    }

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/GameKind.fxml"));
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
        setBackGround(controller.story, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.custom, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }

    @Override
    public void help() {
        view.showHelpForGameKindMenu();
    }

    /*@Override
    public void handleCommand(String command) {
        switch (command){
            case "1":
                BattleMenuPage.setGameKind("story");
                view.getPages().push(new MissionsMenuPage());
                break;
            case "2":
                BattleMenuPage.setGameKind("custom game");
                view.getPages().push(new GameMoodMenuPage(stage));
                break;
            case "back":
                view.back();
                break;
            case "help":
                help();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }*/

    @Override
    public void showMenu() {
        view.show("Select Game Kind:");
        view.show("1: story\n2: custom game\n");
    }
}
