package view.BattleMenu;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.View;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MissionsMenuPage extends Page {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    //private Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    public MissionsMenuPage(){
        start();
    }


    private static Pane root = new Pane();
    private MissionController controller;

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Mission.fxml"));
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
        setBackGround(controller.mission1, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.mission2, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.mission3, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }

    @Override
    public void help() {
        view.showHelpForMissionMenu();
    }

    /*@Override
    public void handleCommand(String command) {
        switch (command){
            case "1":
                BattleMenuPage.setMission("1");
                controller.setAiMainDeck(1);
                battleMenuPage.setSecondUser(AI.getAI());
                view.getPages().push(new Match());
                break;
            case "2":
                BattleMenuPage.setMission("2");
                controller.setAiMainDeck(2);
                battleMenuPage.setSecondUser(AI.getAI());
                view.getPages().push(new Match());
                break;
            case "3":
                BattleMenuPage.setMission("3");
                controller.setAiMainDeck(3);
                battleMenuPage.setSecondUser(AI.getAI());
                view.getPages().push(new Match());
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
        view.show("Select Mission:");
        view.show("" +
                "1: Hero Name: Dive Sefid  |  Mood: kill opponent hero\n" +
                "2: Hero Name: Zahhak      |  Mood: collect and keep flags\n" +
                "3: Hero Name: Arash       |  Mood: collect half flags\n");
    }
}
