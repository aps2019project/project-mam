package Controller.GameController;

import Model.enums.ErrorType;
import Model.game.Game;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class MapController {
    private static MapController ourInstance = new MapController();

    public static MapController getInstance() {
        return ourInstance;
    }

    private MapController() {
    }

    private Rectangle[][] cells;
    private Pane pane;
    private Label label;

    private boolean isSelected = false;


    public void initialize(Rectangle[][] cells, Pane pane, Label label){
        this.label = label;
        this.cells = cells;
        this.pane = pane;
        initCells();
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

            if (isSelected){
                moveCard(String.valueOf(x), String.valueOf(y));
            }
            else if (rectangle.getId() != null) {
                selectCard(rectangle.getId());
                isSelected = true;
            }
        });
    }

    public void selectCard(String cardId) {
        if (Game.getInstance().isCardInPlayerCellCard(Integer.parseInt(cardId))) {
            Game.getInstance().selectCard(Integer.parseInt(cardId));
            label.setText(cardId + " selected");
        } else label.setText("please select your card");
    }

    public void moveCard(String x, String y) {
        if (Game.getInstance().getCurrentCard().isCanMove()) {
            if (Game.getInstance().cardCanMove(Integer.parseInt(x), Integer.parseInt(y))) {
                Game.getInstance().moveCurrentCardTo(Integer.parseInt(x), Integer.parseInt(y));
                StringBuilder message = new StringBuilder();
                message.append(Game.getInstance().getCurrentCard().getId()).append(" moved to ");
                message.append(x).append(" ").append(y);
                ErrorType.SUCCESSFUL_MOVING_CARD.setMessage(message.toString());
                label.setText(ErrorType.SUCCESSFUL_MOVING_CARD.getMessage());
            } else label.setText(ErrorType.INVALID_TARGET.getMessage());
        } else label.setText(ErrorType.CARD_CAN_NOT_MOVE.getMessage());
    }
}
