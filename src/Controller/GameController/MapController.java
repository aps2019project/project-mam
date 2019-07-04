package Controller.GameController;

import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Cell;
import Model.game.Game;
import Model.size.Coordinate;
import Model.size.Resolution;
import command.CommandType;
import command.ServerCommand;
import command.clientCommand.*;
import gson.GsonWriter;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import view.pages.Page;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.floor;
import static java.lang.Math.negateExact;

public class MapController {
    private static MapController ourInstance = new MapController();

    public static MapController getInstance() {
        return ourInstance;
    }

    private MapController() {
    }

    private GameController controller;
    private Game game = Game.getInstance();
    private ImageController imageController = ImageController.getInstance();
    private AnimationController animationCtrl = AnimationController.getInstance();
    private AudioController audioCtrl = AudioController.getInstance();

    private Rectangle[][] cells;
    private Pane pane;
    private Label label;
    private ArrayList<Circle> handCards;

    private boolean isSelected = false;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void initialize(Rectangle[][] cells, Pane pane, Label label, ArrayList<Circle> handCards) {
        this.label = label;
        this.cells = cells;
        this.pane = pane;
        this.handCards = handCards;
        game = Game.getInstance();
        initCells();
        Coordinate.getInstance().setHdCoordinate();
        imageController.initItemImage();
        updatePlayerName();
        updatePlayersMana();
        updateHand();
        updateItems();
        updateMap();
        imageController.initHeroImage();
        updateNextCard();
        imageController.addFlags(getPane());
        imageController.addCollectibles(getPane());
    }

    private String text;

    public Rectangle[][] getCells() {
        return cells;
    }

    public Pane getPane() {
        return pane;
    }

    private String handCardId = "-1";
    private boolean handCardSelected = false;

    final double SPACE = 5;
    final int ROW = 5;
    final int COLUMN = 9;

    final double startX = 650;
    final double startY = 320;
    final double xStep = (808 - SPACE * COLUMN) / COLUMN;
    final double yStep = (410 - SPACE * ROW) / ROW;

    public void initCells() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++) {
                double x = (xStep + SPACE) * j + startX;
                double y = (yStep + SPACE) * i + startY;
                Rectangle rectangle = new Rectangle(x, y, xStep, yStep);
                rectangle.setFill(Color.BLACK);
                rectangle.setOpacity(0.2);
                setOnRecClicked(rectangle, i, j);
                pane.getChildren().add(rectangle);
                cells[i][j] = rectangle;
            }
    }

    private void setOnCard2EnteredAndExited() {
        for (Map.Entry<Integer, ImageView> entry : imageController.getViews2().entrySet()) {
            entry.getValue().setOnMouseEntered(event -> {
                text = label.getText();
                Card card = game.getMap().getSecondPlayerCellCard().get(entry.getKey()).getCard();
                label.setText("Name: " + card.getName() + "  AP: " + card.getAP() + "  HP: " + card.getHP());
            });
            entry.getValue().setOnMouseExited(event -> {
                label.setText(text);
            });
        }
    }

    private void setOnCard1EnteredAndExited() {
        for (Map.Entry<Integer, ImageView> entry : imageController.getViews1().entrySet()) {
            if (game.getMap().getFirstPlayerCellCard().containsKey(entry.getKey()))
                entry.getValue().setOnMouseEntered(event -> {
                    text = label.getText();
                    Card card = game.getMap().getFirstPlayerCellCard().get(entry.getKey()).getCard();
                    label.setText("Name: " + card.getName() + "  AP: " + card.getAP() + "  HP: " + card.getHP());
                });

            entry.getValue().setOnMouseExited(event -> {
                label.setText(text);
            });
        }
    }

    private void setOnRecClicked(Rectangle rectangle, int x, int y) {
        rectangle.setOnMouseClicked(event -> {
            if (!game.isMyTurn())
                return;
            if (handCardSelected) {
                if (game.isMulti())
                    GsonWriter.sendClientCommand(new InsertCmd(game.getFirstPlayerHand().get(Integer.parseInt(handCardId)).getName(), x, y), Page.getOutput());
                insertCard(game.getFirstPlayerHand().get(Integer.parseInt(handCardId)).getName(), x, y);
                //removeIdFromHand(Integer.parseInt(handCardId));
                //updateMap();
                //updateHand();
                //setOnHandClick();
                handCardSelected = false;
            } else if (isSelected) {
                if ((game.isMyTurn() && rectangle.getFill() == Color.BLUE)
                        || (game.isOppTurn() && rectangle.getFill() == Color.RED)) {
                    if (game.isMulti())
                        GsonWriter.sendClientCommand(new AttackCmd(rectangle.getId()), Page.getOutput());
                    attack(Integer.parseInt(rectangle.getId()));
                    //updateMap();
                    isSelected = false;
                } else {
                    moveCard(x, y);
                    if (game.isMulti())
                        GsonWriter.sendClientCommand(new MoveCmd(x, y), Page.getOutput());
                    //updateMap();
                    isSelected = false;
                }
            } else if (rectangle.getId() != null) {
                if (game.isMyTurn() && rectangle.getFill() == Color.RED) {
                    selectCard(rectangle.getId());
                    if (game.isMulti())
                        GsonWriter.sendClientCommand(new SelectCmd(rectangle.getId()), Page.getOutput());
                    isSelected = true;
                } else if (game.isOppTurn() && rectangle.getFill() == Color.BLUE) {
                    selectCard(rectangle.getId());
                    if (game.isMulti())
                        GsonWriter.sendClientCommand(new SelectCmd(rectangle.getId()), Page.getOutput());
                    isSelected = true;
                } else label.setText("please select your card");
            }
            text = label.getText();
        });
    }

    private void setOnHandClick() {
        for (Map.Entry<Integer, ImageView> entry : imageController.getViewsHand().entrySet()) {
            entry.getValue().setOnMouseClicked(event -> {
                if (!game.isMyTurn())
                    return;
                handCardId = String.valueOf(entry.getKey());
                handCardSelected = true;
                label.setText(handCardId + " from hand selected");
                text = label.getText();
            });
        }
    }

    private void setOnHandEnteredAndExited() {
        for (Map.Entry<Integer, ImageView> entry : imageController.getViewsHand().entrySet()) {
            entry.getValue().setOnMouseEntered(event -> {
                text = label.getText();
                Card card = game.getFirstPlayerHand().get(entry.getKey());
                label.setText("Name: " + card.getName() + " AP:" + card.getAP() + " HP:" + card.getHP() + " MP:" + card.getMP());
            });

            entry.getValue().setOnMouseExited(event -> {
                label.setText(text);
            });
        }
    }

    private void setOnNextEnteredAndExited() {
        Card card = game.getNextFirstPlayerCard();
        imageController.getViews1().get(card.getId()).setOnMouseEntered(event -> {
            text = label.getText();
            label.setText("Name: " + card.getName() + "  AP: " + card.getAP() + "  HP: " + card.getHP());
        });

        imageController.getViews1().get(card.getId()).setOnMouseExited(event -> {
            label.setText(text);
        });
    }

    private void removeIdFromHand(int id) {
        pane.getChildren().remove(imageController.getViewsHand().get(id));
        imageController.getViewsHand().remove(id);
        if (!controller.handCardsMana.get(4).getText().equals("-"))
            controller.handCardsMana.get(4).setText("-");
        else if (!controller.handCardsMana.get(3).getText().equals("-"))
            controller.handCardsMana.get(3).setText("-");
        else if (!controller.handCardsMana.get(2).getText().equals("-"))
            controller.handCardsMana.get(2).setText("-");
        else if (!controller.handCardsMana.get(1).getText().equals("-"))
            controller.handCardsMana.get(1).setText("-");
        else if (!controller.handCardsMana.get(0).getText().equals("-"))
            controller.handCardsMana.get(0).setText("-");
    }

    private void removeIdFromHand(int id, int index) {
        pane.getChildren().remove(imageController.getViewsHand().get(id));
        imageController.getViewsHand().remove(id);
    }

    public void removeNextCard() {
        pane.getChildren().remove(imageController.getViews1().get(game.getNextFirstPlayerCard().getId()));
        imageController.getViews1().remove(game.getNextFirstPlayerCard().getId());
    }

    public void updatePlayerName() {
        controller.firstPlayerName.setText(game.getFirstUser().getName());
        controller.secondPlayerName.setText(game.getSecondUser().getName());
    }

    public void updatePlayersMana() {
        controller.firstPlayerMana.setText(String.valueOf(game.getFirstPlayerMana()) + "/" + game.getBasicMana1());
        controller.secondPlayerMana.setText(String.valueOf(game.getSecondPlayerMana()) + "/" + game.getBasicMana2());
    }

    public void updateItems() {
        controller.item1.setId(String.valueOf(game.getFirstPlayerDeck().getItem().getId()));
        controller.item2.setId(String.valueOf(game.getSecondPlayerDeck().getItem().getId()));
    }

    public void updateHand() {
        int X = 410;
        int Y = 860;
        int STEP = 195;
        int RADIUS = 150;

        if (Coordinate.getInstance().getResolution() == Resolution.HD) {
            X = 265;
            Y = 565;
            RADIUS = 110;
            STEP = 130;
        }
        int counter = 0;
        for (Map.Entry<Integer, Card> entry : game.getFirstPlayerHand().entrySet()) {
            removeIdFromHand(entry.getValue().getId(), counter);
            controller.handCards.get(counter).setId(String.valueOf(entry.getValue().getId()));
            controller.handCardsMana.get(counter).setText(String.valueOf(entry.getValue().getMP()));
            imageController.addCard(X + counter * STEP, Y, entry.getValue(), RADIUS, 1);
            counter++;
        }
        setOnHandClick();
        setOnHandEnteredAndExited();
    }

    public void updateNextCard() {
        int X = 135;
        int Y = 785;
        int RADIUS = 200;

        if (Coordinate.getInstance().getResolution() == Resolution.HD) {
            X = 86;
            Y = 505;
            RADIUS = 150;
        }

        if (game.getNextFirstPlayerCard() != null) {
            controller.nextCard.setId(String.valueOf(game.getNextFirstPlayerCard().getId()));
            imageController.addCard(X, Y, game.getNextFirstPlayerCard(), RADIUS, 2);
            setOnNextEnteredAndExited();
        }
    }

    public void updateMap() {

        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setFill(Color.RED);
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setId(String.valueOf(entry.getValue().getCard().getId()));
        }
        setOnCard1EnteredAndExited();

        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setFill(Color.BLUE);
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setId(String.valueOf(entry.getValue().getCard().getId()));
        }
        setOnCard2EnteredAndExited();

        //imageController.addFlags(getPane());
    }

    /*public void sendCommand(<? extends ClientCommand>){
        GsonWriter.sendClientCommand(command, Page.getOutput());
    }*/

    public void selectCard(String cardId) {
        audioCtrl.onSelect();
        game.selectCard(Integer.parseInt(cardId));
        label.setText(cardId + " selected");
    }

    public void moveCard(int x, int y) {
        if (game.getCurrentCard().isCanMove()) {
            if (game.cardCanMove(x, y)) {
                cells[game.getCurrentCard().getRow()][game.getCurrentCard().getColumn()].setFill(Color.BLACK);
                game.moveCurrentCardTo(x, y);
                audioCtrl.onMove();
                animationCtrl.moveTo(imageController.getView(game.isMyTurn(), game.getCurrentCard().getId()),
                        game.getCurrentCard(),
                        cells[x][y].getX() + (xStep - 15) / 2, cells[x][y].getY() + (yStep - 35) / 2);
                imageController.getView(game.isMyTurn(), game.getCurrentCard().getId()).setX(cells[x][y].getX() - 15);
                imageController.getView(game.isMyTurn(), game.getCurrentCard().getId()).setY(cells[x][y].getY() - 35);
                imageController.updateFlags(getPane(), game.getMap().getCells()[game.getCurrentCard().getRow()][game.getCurrentCard().getColumn()]);
                imageController.updateCollect(getPane(), game.getMap().getCells()[game.getCurrentCard().getRow()][game.getCurrentCard().getColumn()]);
                StringBuilder message = new StringBuilder();
                message.append(game.getCurrentCard().getId()).append(" moved to ");
                message.append(x).append(" ").append(y);
                ErrorType.SUCCESSFUL_MOVING_CARD.setMessage(message.toString());
                label.setText(ErrorType.SUCCESSFUL_MOVING_CARD.getMessage());
                updateMap();
            } else label.setText(ErrorType.INVALID_TARGET.getMessage());
        } else label.setText(ErrorType.CARD_CAN_NOT_MOVE.getMessage());
    }

    public void attack(int oppId) {
        if (game.getCurrentCard().isCanAttack()) {
            if (game.isCardInOppPlayerCellCard(oppId)) {
                if (game.isOppAvailableForAttack(oppId, game.getCurrentCard().getId(), game.getTurn())) {
                    animationCtrl.attack(imageController.getView(game.isMyTurn(), game.getCurrentCard().getId()), game.getCurrentCard());
                    if (game.canCounterAttack(game.getCurrentCard().getId(), oppId))
                        animationCtrl.counterAttack(imageController.getView(game.isOppTurn(), oppId), game.getCard(game.isOppTurn(), oppId));
                    audioCtrl.onAttack();
                    game.attack(oppId);
                    label.setText(ErrorType.SUCCESSFUL_ATTACK.getMessage());
                    updateMap();
                } else label.setText(ErrorType.UNAVAILABLE_OPP_ATTACK.getMessage());
            } else label.setText(ErrorType.INVALID_CARD_ID.getMessage());
        } else {
            StringBuilder message = new StringBuilder();
            message.append("card with ").append(game.getCurrentCard().getId()).append(" can't attack");
            ErrorType.CARD_CAN_NOT_ATTACK.setMessage(message.toString());
            label.setText(ErrorType.CARD_CAN_NOT_ATTACK.getMessage());
        }
    }

    public void insertCard(String cardName, int x, int y) {
        if (game.isCardInPlayerHand(cardName)) {
            if (game.haveEnoughMana(cardName)) {
                if ((game.isCellValidForInsertMinion(x, y) && game.getCardInHand(cardName).getCardType().equalsIgnoreCase("minion")) ||
                        (game.isCellValidForInsertSpell(game.getCardInHand(cardName), x, y) && game.getCardInHand(cardName).getCardType().equalsIgnoreCase("spell"))) {
                    game.insertPlayerCard(cardName, x, y);
                    audioCtrl.onInsert();
                    if (game.getCurrentCard().getCardType().equals("minion"))
                        imageController.addCard(x, y, game.getCurrentCard(), game.getTurn());
                    else {
                        animationCtrl.insertSpell(cells[x][y], game.getCurrentCard(), getPane());
                    }
                    StringBuilder message = new StringBuilder();
                    message.append(cardName).append(" with ").append(game.getCurrentCard().getId());
                    message.append(" inserted to ( ").append(x).append(", ").append(y).append(" )");
                    ErrorType.SUCCESSFUL_INSERTING_CARD.setMessage(message.toString());
                    label.setText(ErrorType.SUCCESSFUL_INSERTING_CARD.getMessage());
                    updatePlayersMana();
                    removeIdFromHand(Integer.parseInt(handCardId));
                    updateMap();
                    updateHand();
                } else label.setText(ErrorType.INVALID_TARGET.getMessage());
            } else label.setText(ErrorType.MANA_IS_NOT_ENOUGH_INSERT.getMessage());
        } else label.setText(ErrorType.INVALID_CARD_NAME.getMessage());
    }


}
