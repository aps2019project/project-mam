package Model.size;

import Controller.GameController.GameController;
import Controller.GameController.MapController;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sun.applet.Main;
import view.BattleMenu.MainBattleMenuPage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Coordinate {
    private static final Coordinate instance = new Coordinate();

    private Coordinate() {
        resolution = Resolution.FULL_HD;
    }

    public static Coordinate getInstance() {
        return instance;
    }

    private Resolution resolution;
    private GameController gameCtrl;

    public Resolution getResolution() {
        if (resolution != null)
            return resolution;
        setResolution();
        return resolution;
    }

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
        setButtonCoordinate();
    }

    public void setHandCoordinate() {
        gameCtrl.handCards.get(0).setCenterX(322);
        gameCtrl.handCards.get(0).setCenterY(630);
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
                gameCtrl.cells[i][j].setX(x);
                gameCtrl.cells[i][j].setY(y);
                gameCtrl.cells[i][j].setWidth(xStep);
                gameCtrl.cells[i][j].setHeight(yStep);
            }
    }

    private void setPaneCoordinate() {
        gameCtrl.pane.setPrefWidth(1280);
        gameCtrl.pane.setPrefHeight(720);
        //setBackGround();
    }

    private void setButtonCoordinate() {
        gameCtrl.endTurn.setX(1000);
        gameCtrl.endTurn.setY(579);
        gameCtrl.endTurn.relocate(990, 575);
        gameCtrl.endTurn.setFitWidth(230);
        gameCtrl.endTurn.setFitHeight(70);

        gameCtrl.exit.setX(1100);
        gameCtrl.exit.setY(645);
        gameCtrl.exit.relocate(1100, 645);
        gameCtrl.exit.setFitHeight(34);
        gameCtrl.exit.setFitWidth(125);
    }

}
