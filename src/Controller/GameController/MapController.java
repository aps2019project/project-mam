package Controller.GameController;

import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Cell;
import Model.game.Game;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.abs;

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
        initCells();
        imageController.initItemImage();
        updatePlayerName();
        updatePlayersMana();
        updateHand();
        updateItems();
        updateMap();
        imageController.initHeroImage();
        updateNextCard();
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
    //-----------------------jimi-------
        /*final double startX = 450;
        final double startY = 190;
        final double xStep = (500 - SPACE * COLUMN) / COLUMN;
        final double yStep = (300 - SPACE * ROW) / ROW;*/
//------------------feri------------
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
            if (handCardSelected) {
                insertCard(game.getFirstPlayerHand().get(Integer.parseInt(handCardId)).getName(), String.valueOf(x), String.valueOf(y));
                removeIdFromHand(Integer.parseInt(handCardId));
                updateMap();
                updateHand();
                //setOnHandClick();
                handCardSelected = false;
            } else if (isSelected) {
                if ((game.getTurn() % 2 == 1 && rectangle.getFill() == Color.BLUE)
                        || (game.getTurn() % 2 == 0 && rectangle.getFill() == Color.RED)) {
                    attack(Integer.parseInt(rectangle.getId()));
                    updateMap();
                    isSelected = false;
                } else {
                    moveCard(x, y);
                    updateMap();
                    isSelected = false;
                }
            } else if (rectangle.getId() != null) {
                if (game.getTurn() % 2 == 1 && rectangle.getFill() == Color.RED) {
                    selectCard(rectangle.getId());
                    isSelected = true;
                } else if (game.getTurn() % 2 == 0 && rectangle.getFill() == Color.BLUE) {
                    selectCard(rectangle.getId());
                    isSelected = true;
                } else label.setText("please select your card");
            }
            text = label.getText();
        });
    }

    private void setOnHandClick() {
        for (Map.Entry<Integer, ImageView> entry : imageController.getViewsHand().entrySet()) {
            entry.getValue().setOnMouseClicked(event -> {
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
                label.setText("Name: " + card.getName() + "  AP: " + card.getAP() + "  HP: " + card.getHP());
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
        controller.handCardsMana.get(4).setText("-");
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
        controller.firstPlayerMana.setText(String.valueOf(game.getFirstPlayerMana()));
        controller.secondPlayerMana.setText(String.valueOf(game.getSecondPlayerMana()));
    }

    public void updateItems() {
        controller.item1.setId(String.valueOf(game.getFirstPlayerDeck().getItem().getId()));
        controller.item2.setId(String.valueOf(game.getSecondPlayerDeck().getItem().getId()));
    }

    public void updateHand() {
        int counter = 0;
        for (Map.Entry<Integer, Card> entry : game.getFirstPlayerHand().entrySet()) {
            removeIdFromHand(entry.getValue().getId());
            controller.handCards.get(counter).setId(String.valueOf(entry.getValue().getId()));
            controller.handCardsMana.get(counter).setText(String.valueOf(entry.getValue().getMP()));
            imageController.addCard(410 + counter * 195, 860.0, entry.getValue(), 150, 1);
            counter++;
        }
        setOnHandClick();
        setOnHandEnteredAndExited();
    }

    public void updateNextCard() {
        controller.nextCard.setId(String.valueOf(game.getNextFirstPlayerCard().getId()));
        imageController.addCard(135, 785, game.getNextFirstPlayerCard(), 200, 2);
        setOnNextEnteredAndExited();
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
    }


    public void selectCard(String cardId) {
        game.selectCard(Integer.parseInt(cardId));
        label.setText(cardId + " selected");
    }

    public void moveCard(int x, int y) {
        if (game.getCurrentCard().isCanMove()) {
            if (game.cardCanMove(x, y)) {
                cells[game.getCurrentCard().getRow()][game.getCurrentCard().getColumn()].setFill(Color.BLACK);
                game.moveCurrentCardTo(x, y);
                animationCtrl.moveTo(imageController.getView(game.getTurn() % 2, game.getCurrentCard().getId()),
                        game.getCurrentCard(),
                        cells[x][y].getX() + (xStep - 15) / 2, cells[x][y].getY() + (yStep - 35) / 2);
                imageController.getView(game.getTurn() % 2, game.getCurrentCard().getId()).setX(cells[x][y].getX() - 15);
                imageController.getView(game.getTurn() % 2, game.getCurrentCard().getId()).setY(cells[x][y].getY() - 35);
                StringBuilder message = new StringBuilder();
                message.append(game.getCurrentCard().getId()).append(" moved to ");
                message.append(x).append(" ").append(y);
                ErrorType.SUCCESSFUL_MOVING_CARD.setMessage(message.toString());
                label.setText(ErrorType.SUCCESSFUL_MOVING_CARD.getMessage());
            } else label.setText(ErrorType.INVALID_TARGET.getMessage());
        } else label.setText(ErrorType.CARD_CAN_NOT_MOVE.getMessage());
    }

    public void attack(int oppId) {
        if (game.getCurrentCard().isCanAttack()) {
            if (game.isCardInOppPlayerCellCard(oppId)) {
                if (game.isOppAvailableForAttack(oppId, game.getCurrentCard().getId())) {
                    animationCtrl.attack(imageController.getView(game.getTurn() % 2, game.getCurrentCard().getId()), game.getCurrentCard());
                    int changedTurn = abs(game.getTurn() % 2 - 1);
                    animationCtrl.conterAttack(imageController.getView(changedTurn, oppId), game.getCard(changedTurn, oppId));
                    game.attack(oppId);
                    label.setText(ErrorType.SUCCESSFUL_ATTACK.getMessage());
                } else label.setText(ErrorType.UNAVAILABLE_OPP_ATTACK.getMessage());
            } else label.setText(ErrorType.INVALID_CARD_ID.getMessage());
        } else {
            StringBuilder message = new StringBuilder();
            message.append("card with ").append(game.getCurrentCard().getId()).append(" can't attack");
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
        if (game.isCardInOppPlayerCellCard(Integer.parseInt(oppCardId))) {
            game.comboAttack(Integer.parseInt(oppCardId), attackersId);
        } else label.setText(ErrorType.INVALID_CARD_ID.getMessage());
    }

    public void useSP(String x, String y) {
        if (game.getCurrentCard() instanceof Minion && game.getCurrentCard().getSPActivationTime() == SPActivationTime.ON_SPAWN) {
            if (game.getCurrentCard() instanceof Hero && game.getCurrentCard().getCooldown() == 0) {
                game.useSP(Integer.parseInt(x), Integer.parseInt(y));
            } else label.setText(ErrorType.MANA_IS_NOT_ENOUGH_USE_SP.getMessage());
        } else label.setText(ErrorType.CARD_HAVE_NOT_SP.getMessage());
    }

    public void insertCard(String cardName, String x, String y) {
        if (game.isCardInPlayerHand(cardName)) {
            if (game.haveEnoughMana(cardName)) {
                if (game.isCellValidForInsert(Integer.parseInt(x), Integer.parseInt(y))) {
                    game.insertPlayerCard(cardName, Integer.parseInt(x), Integer.parseInt(y));

                    if (game.getCurrentCard().getCardType().equals("minion"))
                        imageController.addCard(Integer.parseInt(x), Integer.parseInt(y), game.getCurrentCard(), game.getTurn());
                    else {
                        animationCtrl.insertSpell(cells[Integer.parseInt(x)][Integer.parseInt(y)], game.getCurrentCard(), getPane());
                    }
                    StringBuilder message = new StringBuilder();
                    message.append(cardName).append(" with ").append(game.getCurrentCard().getId());
                    message.append(" inserted to ( ").append(x).append(", ").append(y).append(" )");
                    ErrorType.SUCCESSFUL_INSERTING_CARD.setMessage(message.toString());
                    label.setText(ErrorType.SUCCESSFUL_INSERTING_CARD.getMessage());
                } else label.setText(ErrorType.INVALID_TARGET.getMessage());
            } else label.setText(ErrorType.MANA_IS_NOT_ENOUGH_INSERT.getMessage());
        } else label.setText(ErrorType.INVALID_CARD_NAME.getMessage());
    }

}
