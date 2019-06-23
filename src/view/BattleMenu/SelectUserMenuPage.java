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

public class SelectUserMenuPage extends Page {

    //Controller controller = Controller.getInstance();
    View view = View.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();

    public SelectUserMenuPage(){
        start();
    }


    private static Pane root = new Pane();
    private SelectUserController controller;



    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/SelectUser.fxml"));
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
        setBackGround(controller.select, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));
        controller.showUsers();


    }

    @Override
    public void help() {
        view.showHelpForSelectUserMenu();
    }

    /*@Override
    public void handleCommand(String command) {
        if (command.matches("select user .*")) {
            String userName = command.split(" ")[2];
            if (controller.isUserNameValid(userName)) {
                if (controller.isMainDeckValid(userName)) {
                    battleMenuPage.setSecondUser(controller.getSecondUser(userName));
                    view.getPages().push(new GameMoodMenuPage(stage));
                } else view.printError(ErrorType.INVALID_DECK_2);
            } else view.printError(ErrorType.INVALID_USERNAME);
        }else if (command.equalsIgnoreCase("help")) {
            help();
        }else if (command.equalsIgnoreCase("back")){
            view.back();
        }
        else view.printError(ErrorType.INVALID_COMMAND);
    }*/

    @Override
    public void showMenu() {
        //controller.showUsers();
        view.show("Select Second User:");
    }
}
