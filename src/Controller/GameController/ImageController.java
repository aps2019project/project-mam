package Controller.GameController;

import Model.card.Card;
import Model.game.Game;
import Model.game.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageController {
    public static final ImageController instance = new ImageController();

    private ImageController() {
    }

    public static ImageController getInstance() {
        return instance;
    }

   // private ArrayList<ImageView> views = new ArrayList<>();
    private HashMap<Integer, ImageView> views = new HashMap<>();

    public HashMap<Integer, ImageView> getViews() {
        return views;
    }

    public void initCardImage() {
        File dir = new File("resources/unit_gifs");
        ArrayList<String> path = new ArrayList<>();
        for (String s : dir.list()) {
            path.add("resources/unit_gifs/" + s);
        }
        int counter = 0;
        for (Card card : Game.getInstance().getFirstPlayerDeck().getCards()) {
            card.setImageAddress(path.get(counter++));
        }
        Game.getInstance().getFirstPlayerDeck().getHero().setImageAddress(path.get(counter++));
        for (Card card : Game.getInstance().getSecondPlayerDeck().getCards()) {
            card.setImageAddress(path.get(counter++));
        }
        Game.getInstance().getSecondPlayerDeck().getHero().setImageAddress(path.get(counter));
    }

    public void addCard(int row, int column, Card card) {
        try {
            ImageView view = new ImageView(new Image(new FileInputStream(card.getImageAddress())));
            view.setY(MapController.getInstance().getCells()[row][column].getY()-35);
            view.setX(MapController.getInstance().getCells()[row][column].getX()-15);
            MapController.getInstance().getPane().getChildren().add(view);
            views.put(card.getId(), view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCard(double x, double y, Card card, int size) {
        try {
            ImageView view = new ImageView(new Image(new FileInputStream(card.getImageAddress())));
            view.setY(y);
            view.setX(x);
            //view.resize(size, size);
            view.setFitWidth(size);
            view.setFitHeight(size);
            MapController.getInstance().getPane().getChildren().add(view);
            views.put(card.getId(), view);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
