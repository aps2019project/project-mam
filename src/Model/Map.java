package Model;

import java.util.HashMap;

import static java.lang.Math.abs;

public class Map {
    private static final int ROW_NUMBER = 5;
    private static final int COLUMN_NUMBER = 9;
    private Cell[][] cells;
    private HashMap<Integer, Card> firstPlayerCellCard;
    private HashMap<Integer, Card> secondPlayerCellCard;


    public Map() {
        cells = new Cell[ROW_NUMBER][COLUMN_NUMBER];
    }

    public int getManhatanDistance(Cell firstCell, Cell secondCell) {
        return abs(firstCell.getColumn() - secondCell.getColumn()) + abs(firstCell.getRow() - secondCell.getRow());
    }

    public int getManhatanDistance(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public HashMap<Integer, Card> getFirstPlayerCellCard() {
        return firstPlayerCellCard;
    }

    public HashMap<Integer, Card> getSecondPlayerCellCard() {
        return secondPlayerCellCard;
    }

    public boolean isLocationValid(int x, int y) {
        return x < ROW_NUMBER && x >= 0 && y < COLUMN_NUMBER && y >= 0;
    }




}
