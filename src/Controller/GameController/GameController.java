package Controller.GameController;

import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Game;
import Model.user.AI;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import view.pages.Page;

import java.util.ArrayList;


public class GameController {
    private MapController mapCtrl = MapController.getInstance();
    private Game game = Game.getInstance();
    private ImageController imageCtrl = ImageController.getInstance();
    private AI ai = AI.getInstance();

    public Label firstPlayerName;
    public Label firstPlayerMana;
    public Label secondPlayerName;
    public Label secondPlayerMana;
    public Label item1mana;
    public Label item2mana;
    public Label handCard1mana;
    public Label handCard2mana;
    public Label handCard3mana;
    public Label handCard4mana;
    public Label handCard5mana;
    public Label label;

    public ImageView graveyard;
    public ImageView exit;
    public ImageView help;
    public ImageView endTurn;

    public Circle item1;
    public Circle item2;
    public Circle nextCard;
    public Circle handCard1;
    public Circle handCard2;
    public Circle handCard3;
    public Circle handCard4;
    public Circle handCard5;

    //------------------me-----------
    public Pane pane;
    public ScrollPane graveyardSP;
    public Label graveyardL;

    public Rectangle[][] cells = new Rectangle[5][9];

    public void initializeGame() {
        mapCtrl.setController(this);
        mapCtrl.initialize(cells, pane, label, handCards);
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public ArrayList<Circle> handCards = new ArrayList<>();
    public ArrayList<Label> handCardsMana = new ArrayList<>();

    public void init() {
        handCards.add(handCard1);
        handCards.add(handCard2);
        handCards.add(handCard3);
        handCards.add(handCard4);
        handCards.add(handCard5);

        handCardsMana.add(handCard1mana);
        handCardsMana.add(handCard2mana);
        handCardsMana.add(handCard3mana);
        handCardsMana.add(handCard4mana);
        handCardsMana.add(handCard5mana);
    }

    public void setGraveyard() {
        if (graveyardSP.isVisible())
            graveyardSP.setVisible(false);
        else {
            graveyardSP.setVisible(true);
            StringBuilder info = new StringBuilder();
            for (Card card : game.getFirstPlayerGraveYard()) {
                info.append(card.getInfo()).append("\n");
            }
            graveyardL.setText(info.toString());
        }
    }

    public void setExit() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void setHelp() {
        label.setText("heeeeelp!!!");
    }

    public void setEndTurn() {
        endTurn();
    }

    public void endTurn() {
        if (game.getNextFirstPlayerCard() != null)
            mapCtrl.removeNextCard();
        game.endTurn();
        label.setText("---<End turn>---");
        if (game.isGameEnd())
            label.setText("-----<game ended>------\n" + game.getWinnerName() + " win");
        else {
            mapCtrl.updateHand();
            mapCtrl.updatePlayersMana();
            mapCtrl.updateNextCard();
            mapCtrl.updateMap();
            if (game.getTurn() % 2 == 0 && game.getSecondUser().getName().equals("AI")) {
                ai.getCommand();
            }
        }
    }
}
