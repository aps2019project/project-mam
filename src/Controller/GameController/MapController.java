package Controller.GameController;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MapController {
    private static MapController ourInstance = new MapController();

    public static MapController getInstance() {
        return ourInstance;
    }

    private MapController() {
    }

    private Rectangle[][] cells;
    private Pane pane;

    public void initalize(Rectangle[][] cells, Pane pane){
        this.cells = cells;
        this.pane = pane;
        initCells();
    }

    public Rectangle[][] getCells() {
        return cells;
    }

    public void initCells() {
        final double SPACE = 5;
        final int ROW = 5;
        final int COLUMN = 9;
//-----------------------jimi-------
        final double startX = 450;
        final double startY = 190;
        final double xStep = (500 - SPACE * COLUMN) / COLUMN;
        final double yStep = (300 - SPACE * ROW) / ROW;
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

                setOnRecClicked(rectangle);
                pane.getChildren().add(rectangle);
                cells[i][j] = rectangle;
            }
    }

    private void setOnRecClicked(Rectangle rectangle) {
        rectangle.setOnMouseClicked(event -> {
            if (rectangle.getFill() == Color.WHITE)
                rectangle.setFill(Color.BEIGE);
            else rectangle.setFill(Color.WHITE);
        });
    }










}
