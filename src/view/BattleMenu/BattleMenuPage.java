package view.BattleMenu;

import Controller.*;
import Model.game.Game;
import Model.user.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.pages.MainMenuPage;
import view.pages.Page;

import java.awt.*;
import java.io.*;

public class BattleMenuPage extends Page {

    private static final BattleMenuPage BATTLE_MENU_PAGE = new BattleMenuPage();
    private static String numOfPlayers = null;
    private static String gameKind = null;
    private static String gameMood = null;
    private static String mission = null;
    private static User secondUser = new User("notAi");
    private static int flags = 0;
    private static int baseTurn;

    private static Pane root = new Pane();
    private BattleMenuController controller;

    public BattleMenuPage() {
        start();
    }

    public static BattleMenuPage getInstance() {
        return BATTLE_MENU_PAGE;
    }

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

    public static User getSecondUser() {
        return secondUser;
    }

    public static int getFlags() {
        return flags;
    }

    public static void setBaseTurn(int baseTurn) {
        BattleMenuPage.baseTurn = baseTurn;
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

    public static void setSecondUser(User secondUser) {
        BattleMenuPage.secondUser = secondUser;
    }

    public static void setFlags(int flags) {
        BattleMenuPage.flags = flags;
    }

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

    public static void createSingleGame() {
        new Game(User.user, secondUser, gameMood, gameKind, flags);
        Page.getPages().push(new MainBattleMenuPage());
        User.user.addGameToLastGames(Game.getInstance());
    }

    public static void createMultiGame() {
        new Game(User.user, secondUser, gameMood, gameKind, flags, baseTurn);
        Page.getPages().push(new MainBattleMenuPage());
        User.user.addGameToLastGames(Game.getInstance());
    }
}
