package Controller.GameController;

import Model.card.Card;
import Model.enums.ErrorType;
import Model.game.Cell;
import Model.game.Game;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Map;

public class MapController {
    private static MapController ourInstance = new MapController();

    public static MapController getInstance() {
        return ourInstance;
    }

    private MapController() {
    }

    private GameController controller;
    private Game game = Game.getInstance();

    private Rectangle[][] cells;
    private Pane pane;
    private Label label;

    private boolean isSelected = false;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void initialize(Rectangle[][] cells, Pane pane, Label label) {
        this.label = label;
        this.cells = cells;
        this.pane = pane;
        initCells();
        updatePage();
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public void initCells() {
        final double SPACE = 5;
        final int ROW = 5;
        final int COLUMN = 9;
//-----------------------jimi-------
        final double startX = 650;
        final double startY = 320;
        final double xStep = (808 - SPACE * COLUMN) / COLUMN;
        final double yStep = (410 - SPACE * ROW) / ROW;
//------------------feri------------
        /*final double startX = pane.getLayoutX();
        final double startY = pane.getLayoutY();
        final double xStep = (pane.getPrefWidth() - space * column) / column;
        final double yStep = (pane.getPrefHeight() - space * row) / row;*/

        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++) {
                double x = (xStep + SPACE) * j + startX;
                double y = (yStep + SPACE) * i + startY;
                Rectangle rectangle = new Rectangle(x, y, xStep, yStep);
                rectangle.setFill(Color.WHITE);
                rectangle.setOpacity(0.2);
                setOnRecClicked(rectangle, i, j);
                pane.getChildren().add(rectangle);
                cells[i][j] = rectangle;
            }
    }

    private void setOnRecClicked(Rectangle rectangle, int x, int y) {
        rectangle.setOnMouseClicked(event -> {
            /*if (rectangle.getFill() == Color.WHITE)
                rectangle.setFill(Color.BEIGE);
            else rectangle.setFill(Color.WHITE);*/

            if (isSelected) {
                removeThisId(String.valueOf(game.getCurrentCard().getId()));
                moveCard(x, y);
                updatePage();
                isSelected = false;
            } else if (rectangle.getId() != null) {
                if (Game.getInstance().isCardInPlayerCellCard(Integer.parseInt(rectangle.getId()))) {
                    selectCard(rectangle.getId());
                    isSelected = true;
                } else label.setText("please select your card");
            }
        });
    }

    private void removeThisId(String id) {
        for (Rectangle[] rectangles : cells) {
            for (Rectangle rectangle : rectangles) {
                if (rectangle.getId() != null) {
                    if (rectangle.getId().equals(id)) {
                        rectangle.setId(null);
                        rectangle.setFill(Color.WHITE);
                    }
                }
            }
        }
    }

    public void updatePage() {
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
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setFill(Color.BLUE);
            controller.cells[entry.getValue().getRow()][entry.getValue().getColumn()].setId(String.valueOf(entry.getValue().getCard().getId()));
        }

    }


    public void selectCard(String cardId) {
        Game.getInstance().selectCard(Integer.parseInt(cardId));
        label.setText(cardId + " selected");
    }

    public void moveCard(int x, int y) {
        if (Game.getInstance().getCurrentCard().isCanMove()) {
            if (Game.getInstance().cardCanMove(x, y)) {
                Game.getInstance().moveCurrentCardTo(x, y);
                StringBuilder message = new StringBuilder();
                message.append(Game.getInstance().getCurrentCard().getId()).append(" moved to ");
                message.append(x).append(" ").append(y);
                ErrorType.SUCCESSFUL_MOVING_CARD.setMessage(message.toString());
                label.setText(ErrorType.SUCCESSFUL_MOVING_CARD.getMessage());
            } else label.setText(ErrorType.INVALID_TARGET.getMessage());
        } else label.setText(ErrorType.CARD_CAN_NOT_MOVE.getMessage());
    }


}
