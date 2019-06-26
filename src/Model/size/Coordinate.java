package Model.size;

import Controller.GameController.GameController;
import Controller.GameController.MapController;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sun.applet.Main;
import view.BattleMenu.MainBattleMenuPage;

import java.awt.*;
import java.util.ArrayList;

public class Coordinate {
    private static final Coordinate instance = new Coordinate();

    private Coordinate() {
        resolution = Resolution.FULL_HD;
    }

    public static Coordinate getInstance() {
        return instance;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    private Resolution resolution;
    private GameController gameCtrl;

    public void setResolution() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        if (width == 1366) {
            resolution = Resolution.HD;
        }
    }

    public void setHdCoordinate() {
        setResolution();
        if (resolution == Resolution.FULL_HD)
            return;
        gameCtrl = MainBattleMenuPage.getController();
        setPaneCoordinate();
        setCellCoordinate();
        setHandCoordinate();
        /*backGround.setFitHeight(height);
        backGround.setFitWidth(width);
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);*/
    }

    public void setHandCoordinate() {

    }

    public void setCellCoordinate() {
        final double SPACE = 5;
        final int ROW = 5;
        final int COLUMN = 9;

        final double startX = 450;
        final double startY = 190;
        final double xStep = (500 - SPACE * COLUMN) / COLUMN;
        final double yStep = (300 - SPACE * ROW) / ROW;

        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++) {
                double x = (xStep + SPACE) * j + startX;
                double y = (yStep + SPACE) * i + startY;
                gameCtrl.cells[i][j].relocate(x,y);
                gameCtrl.cells[i][j].setWidth(xStep);
                gameCtrl.cells[i][j].setHeight(yStep);
            }
    }

    private void setPaneCoordinate() {

    }
}
