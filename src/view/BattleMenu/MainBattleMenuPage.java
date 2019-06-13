package view.BattleMenu;

import Controller.GameController.GameController;
import Controller.*;
import Model.card.Card;
import Model.enums.ErrorType;
import Model.game.Cell;
import Model.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import view.View;
import view.pages.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class MainBattleMenuPage extends Page {

    //private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();
    private static Game game = Game.getInstance();
    public static Game getGame(){
        return game;
    }


    private static boolean isStarted = false;

    public MainBattleMenuPage() {
        game = Game.getInstance();
        start();
        init();
        updatePage();
    }


    private static Pane root = new Pane();
    private GameController controller;

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
            controller.initalizeGame();
            Scene scene = new Scene(root);
            stage.setScene(scene);
           // stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() throws FileNotFoundException {
        setBackGround(root, "resources/maps/gameBackgaround.jpg");
        setBackGround(controller.label, "resources/ui/button_primary_middle_glow@2x.png");
        //setBackGround(root, "resources/maps/battlemap5_middleground.png");
        /*setBackGround(controller.story, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.custom, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));
*/
    }

    public void updatePage(){
        /*controller.firstPlayerName.setText(game.getFirstUser().getName());
        controller.secondPlayerName.setText(game.getSecondUser().getName());

        controller.firstPlayerMana.setText(String.valueOf(game.getFirstPlayerMana()));
        controller.secondPlayerMana.setText(String.valueOf(game.getSecondPlayerMana()));

        int counter = 0;
        for (Map.Entry<Integer, Card> entry : game.getFirstPlayerHand().entrySet()) {
            controller.handCards.get(counter).setId(String.valueOf(entry.getValue().getId()));
            controller.handCardsMana.get(counter).setText(String.valueOf(entry.getValue().getMP()));
            counter++;
        }

        *//*controller.handCard2.setId(String.valueOf(game.getFirstPlayerHand().get(1).getId()));
        controller.handCard3.setId(String.valueOf(game.getFirstPlayerHand().get(2).getId()));
        controller.handCard4.setId(String.valueOf(game.getFirstPlayerHand().get(3).getId()));
        controller.handCard5.setId(String.valueOf(game.getFirstPlayerHand().get(4).getId()));*//*

        controller.nextCard.setId(String.valueOf(game.getNextFirstPlayerCard().getId()));

        *//*controller.handCard1mana.setText(String.valueOf(game.getFirstPlayerHand().get(0).getMP()));
        controller.handCard2mana.setText(String.valueOf(game.getFirstPlayerHand().get(1).getMP()));
        controller.handCard3mana.setText(String.valueOf(game.getFirstPlayerHand().get(2).getMP()));
        controller.handCard4mana.setText(String.valueOf(game.getFirstPlayerHand().get(3).getMP()));
        controller.handCard5mana.setText(String.valueOf(game.getFirstPlayerHand().get(4).getMP()));*//*

        controller.item1.setId(String.valueOf(game.getFirstPlayerDeck().getItem().getId()));
        controller.item2.setId(String.valueOf(game.getSecondPlayerDeck().getItem().getId()));

        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            StringBuilder index = new StringBuilder();
            index.append(entry.getValue().getColumn()).append(entry.getValue().getRow());
            for (ImageView cell : controller.cells) {
                if (cell.getAccessibleText().equals(index.toString()))
                    cell.setId(String.valueOf(entry.getKey()));
            }
        }*/
    }


    private void init(){
        /*if (controller.getSecondDeck().getCards().size() != 0)
            battleMenuPage.getSecondUser().setMainDeck(controller.getSecondDeck());*/
        /*game = new Game(battleMenuPage.getFirstUser(), battleMenuPage.getSecondUser(),
                BattleMenuPage.getGameMood(), BattleMenuPage.getGameKind(), 0);*/
    }

    @Override
    public void help() {
        view.show("Game info\nshow my minions \nshow opponent minions \nShow card info [card id] \nSelect [card id]\n" +
                "Use special power (x, y)\nShow hand\nInsert [card name] in (x, y)\nInsert [card name] in x y\nEnd turn\n" +
                "Show collectables\nSelect [collectable id]\nShow info\nShow Next Card\nEnter graveyard\n");

    }

    /*@Override
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
    }*/

    @Override
    public void showMenu() {
        //controller.setGame(game);
        if (!isStarted) {
            isStarted = true;
            view.show("----------< BATTLE >---------");
            view.show("num of player : " + BattleMenuPage.getNumOfPlayers());
            view.show("game kind : " + BattleMenuPage.getGameKind());
            view.show("game mood : " + BattleMenuPage.getGameMood());
            view.show("mission : " + BattleMenuPage.getMission());
        }
    }
}
