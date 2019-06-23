package Controller.GameController;

import Model.card.Card;
import Model.game.Cell;
import Model.game.Game;
import Model.game.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ImageController {
    public static final ImageController instance = new ImageController();

    private ImageController() {
    }

    public static ImageController getInstance() {
        return instance;
    }

    private HashMap<Integer, ImageView> views1 = new HashMap<>();
    private HashMap<Integer, ImageView> views2 = new HashMap<>();
    private HashMap<Integer, ImageView> viewsHand = new HashMap<>();

    public HashMap<Integer, ImageView> getViews1() {
        return views1;
    }

    public HashMap<Integer, ImageView> getViews2() {
        return views2;
    }

    public HashMap<Integer, ImageView> getViewsHand() {
        return viewsHand;
    }

    public void initCardImage() {
        File dir = new File("resources/gif");
        ArrayList<String> path = new ArrayList<>();
        for (String s : dir.list()) {
            path.add("resources/gif/" + s);
        }
        int counter = 0;
        for (Card card : Game.getInstance().getFirstPlayerDeck().getCards()) {
            card.setAttackImage(path.get(counter++));
            card.setBreathingImage(path.get(counter++));
            card.setDeathImage(path.get(counter++));
            card.setRunImage(path.get(counter++));
        }
        Game.getInstance().getFirstPlayerDeck().getHero().setAttackImage(path.get(counter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setBreathingImage(path.get(counter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setDeathImage(path.get(counter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setRunImage(path.get(counter++));
        for (Card card : Game.getInstance().getSecondPlayerDeck().getCards()) {
            card.setAttackImage(path.get(counter++));
            card.setBreathingImage(path.get(counter++));
            card.setDeathImage(path.get(counter++));
            card.setRunImage(path.get(counter++));
        }
        Game.getInstance().getSecondPlayerDeck().getHero().setAttackImage(path.get(counter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setBreathingImage(path.get(counter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setDeathImage(path.get(counter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setRunImage(path.get(counter));

    }

    public void initHeroImage(){
        addCard(Game.getInstance().getMap().getCells()[2][0], 1);
        addCard(Game.getInstance().getMap().getCells()[2][8], 2);
    }

    public void initItemImage() {
        try {
            File dir = new File("resources/items");
            ArrayList<String> path = new ArrayList<>();
            for (String s : dir.list()) {
                path.add("resources/items/" + s);
            }
            Random random = new Random();
            addItem(path, random, 185);

            addItem(path, random, 1615);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addItem(ArrayList<String> path, Random random, int x) throws FileNotFoundException {
        ImageView view = new ImageView(new Image(new FileInputStream(path.get(random.nextInt(path.size() - 1)))));
        view.setY(305);
        view.setX(x);
        view.setFitWidth(120);
        view.setFitHeight(120);
        MapController.getInstance().getPane().getChildren().add(view);
    }

    public void addCard(int row, int column, Card card, int playerTurn) {
        try {
            ImageView view = new ImageView(new Image(new FileInputStream(card.getBreathingImage())));
            view.setY(MapController.getInstance().getCells()[row][column].getY() - 35);
            view.setX(MapController.getInstance().getCells()[row][column].getX() - 15);
            view.setFitWidth(120);
            view.setFitHeight(120);
            MapController.getInstance().getPane().getChildren().add(view);
            if (playerTurn % 2 == 1)
                views1.put(card.getId(), view);
            else views2.put(card.getId(), view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCard(Cell cell, int turn){
        addCard(cell.getRow(), cell.getColumn(), cell.getCard(), turn);
    }

    public void addCard(double x, double y, Card card, int size, int playerTurn) {
        try {
            ImageView view = new ImageView(new Image(new FileInputStream(card.getBreathingImage())));
            view.setY(y);
            view.setX(x);
            view.resize(size, size);
            view.setFitWidth(size);
            view.setFitHeight(size);
            MapController.getInstance().getPane().getChildren().add(view);
            if (playerTurn == 1)
                viewsHand.put(card.getId(), view);
            else views1.put(card.getId(), view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ImageView getView(int turn, int id){
        if (turn % 2 == 1)
            return views1.entrySet().stream().filter(x -> x.getKey() == id).findFirst().get().getValue();
        return views2.entrySet().stream().filter(x -> x.getKey() == id).findFirst().get().getValue();
    }
}
