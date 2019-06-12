package view.BattleMenu;

import Controller.MatchController;
import Model.enums.ErrorType;
import Model.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import view.View;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.IOException;

public class Match extends Page {
    private MatchController controller;
    private Game game;
    private Pane root;

    public Match() {
        start();
    }

    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Match.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();
            initializeImage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() {
        setBackGround(root, "resources/codex/chapter6_background@2x.jpg");
        //setBackGround(controller.map, "resources/maps/me.png");
    }

    /*    @Override
    public void help() {
        view.show("Game info\nshow my minions \nshow opponent minions \nShow card info [card id] \nSelect [card id]\n" +
                "Use special power (x, y)\nShow hand\nInsert [card name] in (x, y)\nInsert [card name] in x y\nEnd turn\n" +
                "Show collectables\nSelect [collectable id]\nShow info\nShow Next Card\nEnter graveyard\n");

    }

    @Override
    public void handleCommand(String command) {
        if (controller.isEnded()) {
            controller.showEnd();
            view.back();
            view.back();
            view.back();
            view.back();
        }

        if (command.matches("game info")) {
            controller.showGameInfo();
        } else if (command.matches("show my minions")) {
            controller.showMyMinions();
        } else if (command.matches("show opponent minions")) {
            controller.showOpMinions();
        } else if (command.matches("show card info .*")) {
            controller.showCardInfo(command.split(" ")[3]);
        } else if (command.matches("select .*")) {  //card
            view.getPages().push(new SelectCardMenu());
            controller.selectCard(command.split(" ")[1]);
        } else if (command.matches("select collectible .*")) {
            controller.selectCollectible(command.split(" ")[2]);
            view.getPages().push(new SelectCollectibleMenu());
        } else if (command.matches("show collectables")) {
            controller.showCollectibles();
        } else if (command.matches("show hand")) {
            controller.showHand();
        } else if (command.matches("insert (\\S*) in \\d \\d")) {
            controller.insertCard(command.split(" ")[1], command.split(" ")[3], command.split(" ")[4]);
        } else if (command.matches("end turn")) {
            controller.endTurn();
        } else if (command.matches("show next card")) {
            controller.showNextCard();
        } else if (command.matches("enter graveyard")) {
            view.getPages().push(new GraveyardMenu());
        } else if (command.matches("help")) {
            help();
        } else if (command.matches("quit")) {
            view.back();
            view.back();
            view.back();
            view.back();
        } else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        controller.setGame(game);
        if (!isStarted) {
            isStarted = true;
            view.show("----------< BATTLE >---------");
            view.show("num of player : " + BattleMenuPage.getNumOfPlayers());
            view.show("game kind : " + BattleMenuPage.getGameKind());
            view.show("game mood : " + BattleMenuPage.getGameMood());
            view.show("mission : " + BattleMenuPage.getMission());
        }
    }*/
}
