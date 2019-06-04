package Model;

import Model.enums.CellEffect;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.abs;

public class Map {
    private static final int ROW_NUMBER = 5;
    private static final int COLUMN_NUMBER = 9;
    private Cell[][] cells;
    private HashMap<Integer, Cell> firstPlayerCellCard = new HashMap<>();
    private HashMap<Integer, Cell> secondPlayerCellCard = new HashMap<>();
    private ArrayList<Cell> flags = new ArrayList<>();


    public Map() {
        cells = new Cell[ROW_NUMBER][COLUMN_NUMBER];
        for (int i = 0 ; i < ROW_NUMBER; i++)
            for (int j = 0; j < COLUMN_NUMBER; j++)
                cells[i][j] = new Cell(i, j, 0, CellEffect.NONE, null);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public HashMap<Integer, Cell> getFirstPlayerCellCard() {
        return firstPlayerCellCard;
    }

    public HashMap<Integer, Cell> getSecondPlayerCellCard() {
        return secondPlayerCellCard;
    }

    public ArrayList<Cell> getFlags() {
        return flags;
    }

    public boolean isTargetInMap(int x, int y) {
        return x < ROW_NUMBER && x >= 0 && y < COLUMN_NUMBER && y >= 0;
    }

    public int getManhatanDistance(Cell firstCell, Cell secondCell) {
        return abs(firstCell.getColumn() - secondCell.getColumn()) + abs(firstCell.getRow() - secondCell.getRow());
    }

    public int getManhatanDistance(int x1, int y1, int x2, int y2) {
        return abs(x1 - x2) + abs(y1 - y2);
    }

    public boolean isCellEmpty(int row, int column) {
        try {
            if (cells[row][column].getCard() == null)
                return true;
        }catch (NullPointerException e){
            return true;
        }
        return false;
    }


}
