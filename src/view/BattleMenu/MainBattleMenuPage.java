package view.BattleMenu;

import Controller.GameController.GameController;
import Model.game.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import view.pages.Page;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainBattleMenuPage extends Page {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private static Game game = Game.getInstance();
    public static Game getGame(){
        return game;
    }

    private static boolean isStarted = false;

    public MainBattleMenuPage() {
        game = Game.getInstance();
        start();
        init();
        //updatePage();
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
            //stage.setFullScreen(true);
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

    public static GameController getController() {
        return controller;
    }

    /*public void updatePage(){
        controller.firstPlayerName.setText(game.getFirstUser().getName());
        controller.secondPlayerName.setText(game.getSecondUser().getName());

        controller.firstPlayerMana.setText(String.valueOf(game.getFirstPlayerMana()));
        controller.secondPlayerMana.setText(String.valueOf(game.getSecondPlayerMana()));

        int counter = 0;
        for (Map.Entry<Integer, Card> entry : game.getFirstPlayerHand().entrySet()) {
            controller.handCards.get(counter).setId(String.valueOf(entry.getValue().getId()));
            controller.handCardsMana.get(counter).setText(String.valueOf(entry.getValue().getMP()));
            counter++;
        }

        controller.nextCard.setId(String.valueOf(game.getNextFirstPlayerCard().getId()));


        controller.item1.setId(String.valueOf(game.getFirstPlayerDeck().getItem().getId()));
        controller.item2.setId(String.valueOf(game.getSecondPlayerDeck().getItem().getId()));


        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setFill(Color.RED);
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setId(String.valueOf(entry.getValue().getCard().getId()));
        }

        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setFill(Color.RED);
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setId(String.valueOf(entry.getValue().getCard().getId()));
        }


        *//*for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            StringBuilder index = new StringBuilder();
            index.append(entry.getValue().getColumn()).append(entry.getValue().getRow());
            for (ImageView cell : controller.cells) {
                if (cell.getAccessibleText().equals(index.toString()))
                    cell.setId(String.valueOf(entry.getKey()));
            }
        }*//*
    }*/


    private void init(){
        /*if (controller.getSecondDeck().getCards().size() != 0)
            battleMenuPage.getSecondUser().setMainDeck(controller.getSecondDeck());*/
        /*game = new Game(battleMenuPage.getFirstUser(), battleMenuPage.getSecondUser(),
                BattleMenuPage.getGameMood(), BattleMenuPage.getGameKind(), 0);*/
    }

}
