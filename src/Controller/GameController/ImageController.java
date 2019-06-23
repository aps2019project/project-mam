package Controller.GameController;

import Model.card.Card;
import Model.game.Cell;
import Model.game.Game;
import Model.game.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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
    private HashMap<Cell, ImageView> viewsFlag = new HashMap<>();

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
        File minions = new File("resources/gif-minion");
        File spells = new File("resources/gif-spell");
        File heros = new File("resources/gif-hero");
        ArrayList<String> minionPath = new ArrayList<>();
        ArrayList<String> spellPath = new ArrayList<>();
        ArrayList<String> heroPath = new ArrayList<>();
        for (String s : minions.list()) {
            minionPath.add("resources/gif-minion/" + s);
        }
        for (String s : spells.list()) {
            spellPath.add("resources/gif-spell/" + s);
        }
        for (String s : heros.list()) {
            heroPath.add("resources/gif-hero/" + s);
        }
        int mCounter = 0;
        int sCounter = 0;
        for (Card card : Game.getInstance().getFirstPlayerDeck().getCards()) {
            if (card.getCardType().equals("minion")) {
                card.setAttackImage(minionPath.get(mCounter++));
                card.setBreathingImage(minionPath.get(mCounter++));
                card.setDeathImage(minionPath.get(mCounter++));
                card.setRunImage(minionPath.get(mCounter++));
            } else {
                card.setBreathingImage(spellPath.get(sCounter++));
                card.setAttackImage(spellPath.get(sCounter++));
            }
        }
        for (Card card : Game.getInstance().getSecondPlayerDeck().getCards()) {
            if (card.getCardType().equals("minion")) {
                card.setAttackImage(minionPath.get(mCounter++));
                card.setBreathingImage(minionPath.get(mCounter++));
                card.setDeathImage(minionPath.get(mCounter++));
                card.setRunImage(minionPath.get(mCounter++));
            } else {
                card.setBreathingImage(spellPath.get(sCounter++));
                card.setAttackImage(spellPath.get(sCounter++));
            }
        }
        Random random = new Random();
        int h = random.nextInt(7);
        int hCounter = h*4;

        Game.getInstance().getFirstPlayerDeck().getHero().setAttackImage(heroPath.get(hCounter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setBreathingImage(heroPath.get(hCounter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setDeathImage(heroPath.get(hCounter++));
        Game.getInstance().getFirstPlayerDeck().getHero().setRunImage(heroPath.get(hCounter++));

        hCounter = random.nextInt(7)*4;
        if (h*4 == hCounter)
            hCounter = (h-1) * 4;

        Game.getInstance().getSecondPlayerDeck().getHero().setAttackImage(heroPath.get(hCounter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setBreathingImage(heroPath.get(hCounter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setDeathImage(heroPath.get(hCounter++));
        Game.getInstance().getSecondPlayerDeck().getHero().setRunImage(heroPath.get(hCounter));
    }

    public void initHeroImage() {
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

    public void addFlags(Pane pane){
        try {
            for (Cell[] cells : Game.getInstance().getMap().getCells()) {
                for (Cell cell : cells) {
                    if (cell.getFlagCount() > 0 && cell.getCard() == null) {
                        ImageView view = new ImageView(new Image(new FileInputStream("resources/loot_crates/mystery/t3/loot_crate_key.png")));
                        view.setFitWidth(24);
                        view.setFitHeight(60);
                        view.setX(MapController.getInstance().getCells()[cell.getRow()][cell.getColumn()].getX() + 30);
                        view.setY(MapController.getInstance().getCells()[cell.getRow()][cell.getColumn()].getY() + 10);
                        viewsFlag.put(cell, view);
                        pane.getChildren().add(view);
                    }
                }
            }
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

    public void addCard(Cell cell, int turn) {
        addCard(cell.getRow(), cell.getColumn(), cell.getCard(), turn);
    }

    public void addCard(double x, double y, Card card, int size, int playerTurn) {
        try {
            ImageView view = new ImageView(new Image(new FileInputStream(card.getBreathingImage())));

            if (card.getCardType().equals("minion")) {
                view.setY(y);
                view.setX(x);
                view.resize(size, size);
                view.setFitWidth(size);
                view.setFitHeight(size);
            } else {
                view.setY(y + size/4 + 15);
                view.setX(x + size/4);
                view.resize(size/2, size/2);
                view.setFitWidth(size/2);
                view.setFitHeight(size/2);
            }
            MapController.getInstance().getPane().getChildren().add(view);
            if (playerTurn % 2 == 1)
                viewsHand.put(card.getId(), view);
            else views1.put(card.getId(), view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ImageView getView(int turn, int id) {
        if (turn % 2 == 1)
            return views1.entrySet().stream().filter(x -> x.getKey() == id).findFirst().get().getValue();
        return views2.entrySet().stream().filter(x -> x.getKey() == id).findFirst().get().getValue();
    }
}
