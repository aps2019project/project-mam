package view.BattleMenu;

import Controller.*;
import Model.enums.ErrorType;
import Model.user.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.View;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BattleMenuPage extends Page {
    //private Controller controller = Controller.getInstance();
    View view = View.getInstance();

    private static final BattleMenuPage BATTLE_MENU_PAGE = new BattleMenuPage();
    private static String numOfPlayers = null;
    private static String gameKind = null;
    private static String gameMood = null;
    private static String mission = null;
    private User secondUser = new User("notAi");
    //private User firstUser = controller.getFirstUser();
    private static int flags;

    public BattleMenuPage(Stage stage) {
        super(stage);
    }

    public BattleMenuPage() {
        start();
    }

    public static BattleMenuPage getInstance() {
        return BATTLE_MENU_PAGE;
    }


    private static Pane root = new Pane();
    private BattleMenuController controller;


    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/BattleMenu.fxml"));
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
        setBackGround(controller.single, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.multi, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }

    @Override
    public void help() {
        view.showHelpForBattleMenu();
    }

    @Override
    public void showMenu() {
        view.show("----------<Battle Menu>---------");
        view.show("1: single player\n2: multi player\n");
    }

    /*@Override
    public void handleCommand(String command) {
        if (!controller.isMainDeckValid()) {
            view.back();
            return;
        }
        switch (command) {
            case "1":
                setNumOfPlayers("single player");
                numOfPlayers = "single player";
                //secondUser = AI.getAI();
                view.getPages().push(new GameKindMenuPage());
                break;
            case "2":
                setNumOfPlayers("multi player");
                numOfPlayers = "multi player";
                view.getPages().push(new SelectUserMenuPage());
                break;
            case "exit":
                view.back();
                break;
            case "help":
                help();
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }*/

    public static String getNumOfPlayers() {
        return numOfPlayers;
    }

    public static String getGameKind() {
        return gameKind;
    }

    public static String getGameMood() {
        return gameMood;
    }

    public static String getMission() {
        return mission;
    }

    /*public User getFirstUser() {
        return controller.getFirstUser();
    }*/

    public User getSecondUser() {
        return secondUser;
    }

    public static int getFlags() {
        return flags;
    }

    public static void setNumOfPlayers(String numOfPlayers) {
        BattleMenuPage.numOfPlayers = numOfPlayers;
    }

    public static void setGameKind(String gameKind) {
        BattleMenuPage.gameKind = gameKind;
    }

    public static void setGameMood(String gameMood) {
        BattleMenuPage.gameMood = gameMood;
    }

    public static void setMission(String mission) {
        BattleMenuPage.mission = mission;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public static void setFlags(int flags) {
        BattleMenuPage.flags = flags;
    }
}
