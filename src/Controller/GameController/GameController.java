package Controller.GameController;

import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Game;
import Model.user.AI;
import javafx.scene.control.Label;
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


    public ImageView cell00;
    public ImageView cell10;
    public ImageView cell20;
    public ImageView cell30;
    public ImageView cell40;
    public ImageView cell50;
    public ImageView cell60;
    public ImageView cell70;
    public ImageView cell80;

    public ImageView cell01;
    public ImageView cell11;
    public ImageView cell21;
    public ImageView cell31;
    public ImageView cell41;
    public ImageView cell51;
    public ImageView cell61;
    public ImageView cell71;
    public ImageView cell81;

    public ImageView cell02;
    public ImageView cell12;
    public ImageView cell22;
    public ImageView cell32;
    public ImageView cell42;
    public ImageView cell52;
    public ImageView cell62;
    public ImageView cell72;
    public ImageView cell82;

    public ImageView cell03;
    public ImageView cell13;
    public ImageView cell23;
    public ImageView cell33;
    public ImageView cell43;
    public ImageView cell53;
    public ImageView cell63;
    public ImageView cell73;
    public ImageView cell83;

    public ImageView cell04;
    public ImageView cell14;
    public ImageView cell24;
    public ImageView cell34;
    public ImageView cell44;
    public ImageView cell54;
    public ImageView cell64;
    public ImageView cell74;
    public ImageView cell84;

    //public ArrayList<ImageView> cells = new ArrayList<>();



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

    public void init(){
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
        this.graveyard = graveyard;
    }

    public void setExit() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void setHelp() {
        this.help = help;
    }

    public void setEndTurn() {
        endTurn();
    }

    public void setItem1() {

    }

    public void setItem2() {
        this.item2 = item2;
    }

    public void endTurn() {
        mapCtrl.removeNextCard();
        game.endTurn();
        label.setText("---<End turn>---");
        mapCtrl.updateHand();
        mapCtrl.updatePlayersMana();
        mapCtrl.updateNextCard();
        mapCtrl.updateMap();
        if(game.getTurn() % 2 == 0 && game.getSecondUser().getName().equals("AI")){
            ai.getCommand();
        }
    }

}
