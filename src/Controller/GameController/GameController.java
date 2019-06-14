package Controller.GameController;

import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Game;
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
        mapCtrl.initialize(cells, pane, label);
        /*imageCtrl.addCard(2, 0, game.getMap().getCells()[2][0].getCard());
        imageCtrl.addCard(2, 8, game.getMap().getCells()[2][8].getCard());*/
    }



    public Rectangle[][] getCells() {
        return cells;
    }
    public ArrayList<Circle> handCards = new ArrayList<>();
    public ArrayList<Label> handCardsMana = new ArrayList<>();

    public void init(){
        /*cells.add(cell00);
        cell00.setAccessibleText("00");
        cells.add(cell10);
        cells.add(cell20);
        cells.add(cell30);
        cells.add(cell40);
        cells.add(cell50);
        cells.add(cell60);
        cells.add(cell70);
        cells.add(cell80);*/

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

    public void setHandCard1() {

    }

    public void setHandCard2() {
        this.handCard2 = handCard2;
    }

    public void setHandCard3() {
        this.handCard3 = handCard3;
    }

    public void setHandCard4() {
        this.handCard4 = handCard4;
    }

    public void setHandCard5() {
        this.handCard5 = handCard5;
    }



    /*public void showGameInfo() {
        view.show(game.getGameInfo());
    }

    public void showMyMinions() {
        view.show(game.showMyMinions());
    }

    public void showOpMinions() {
        view.show(game.showOpMinions());
    }*/

    /*public void showCardInfo(String cardId) {
        if (User.user.getMainDeck().cardIsExist(Integer.parseInt(cardId))) {
            view.show(MainBattleMenuPage.getGame().showCardInfo(Integer.parseInt(cardId)));
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void showHand() {
        view.show(MainBattleMenuPage.getGame().showHand());
    }*/





    public void insertCard(String cardName, String x, String y) {
        if (Game.getInstance().isCardInPlayerHand(cardName)) {
            if (Game.getInstance().haveEnoughMana(cardName)) {
                if (Game.getInstance().isCellValidForInsert(Integer.parseInt(x), Integer.parseInt(y))) {
                    Game.getInstance().insertPlayerCard(cardName, Integer.parseInt(x), Integer.parseInt(y));
                    StringBuilder message = new StringBuilder();
                    message.append(cardName).append(" with ").append(Game.getInstance().getCurrentCard().getId());
                    message.append(" inserted to ( ").append(x).append(", ").append(y).append(" )");
                    ErrorType.SUCCESSFUL_INSERTING_CARD.setMessage(message.toString());
                    label.setText(ErrorType.SUCCESSFUL_INSERTING_CARD.getMessage());
                } else label.setText(ErrorType.INVALID_TARGET.getMessage());
            } else label.setText(ErrorType.MANA_IS_NOT_ENOUGH_INSERT.getMessage());
        } else label.setText(ErrorType.INVALID_CARD_NAME.getMessage());
    }

    public void endTurn() {
        label.setText("---<End turn>---");
        Game.getInstance().endTurn();
    }

    public void attack(String oppCardId) {
        if (Game.getInstance().getCurrentCard().isCanAttack()) {
            if (Game.getInstance().isCardInOppPlayerCellCard(Integer.parseInt(oppCardId))) {
                if (Game.getInstance().isOppAvailableForAttack(Integer.parseInt(oppCardId), Game.getInstance().getCurrentCard().getId())) {
                    Game.getInstance().attack(Integer.parseInt(oppCardId));
                    label.setText(ErrorType.SUCCESSFUL_ATTACK.getMessage());
                } else label.setText(ErrorType.UNAVAILABLE_OPP_ATTACK.getMessage());
            } else label.setText(ErrorType.INVALID_CARD_ID.getMessage());
        } else {
            StringBuilder message = new StringBuilder();
            message.append("card with ").append(Game.getInstance().getCurrentCard().getId()).append(" can't attack");
            ErrorType.CARD_CAN_NOT_ATTACK.setMessage(message.toString());
            label.setText(ErrorType.CARD_CAN_NOT_ATTACK.getMessage());
        }
    }

    public void comboAttack(String oppCardId, ArrayList<String> myCardsId) {
        int[] attackersId = new int[myCardsId.size()];
        int counter = 0;
        for (String cardId : myCardsId) {
            attackersId[counter] = Integer.parseInt(cardId);
            counter++;
        }
        if (Game.getInstance().isCardInOppPlayerCellCard(Integer.parseInt(oppCardId))) {
            Game.getInstance().comboAttack(Integer.parseInt(oppCardId), attackersId);
        } else label.setText(ErrorType.INVALID_CARD_ID.getMessage());
    }

    public void useSP(String x, String y) {
        if (Game.getInstance().getCurrentCard() instanceof Minion && Game.getInstance().getCurrentCard().getSPActivationTime() == SPActivationTime.ON_SPAWN) {
            if (Game.getInstance().getCurrentCard() instanceof Hero && Game.getInstance().getCurrentCard().getCooldown() == 0) {
                Game.getInstance().useSP(Integer.parseInt(x), Integer.parseInt(y));
            } else label.setText(ErrorType.MANA_IS_NOT_ENOUGH_USE_SP.getMessage());
        } else label.setText(ErrorType.CARD_HAVE_NOT_SP.getMessage());
    }


}
