package Model;

public class Map {
    private static final int ROW_NUMBER = 5;
    private static final int COLUMN_NUMBER = 9;
    private CellEffect[][] cellEffects;
    private CollectableItem[][] cellItems;
    private Card[][] cellCards1;
    private Card[][] cellCards2;
    private int[][] cellFlags;

    public Map() {
        cellEffects = new CellEffect[ROW_NUMBER][COLUMN_NUMBER];
        cellItems = new CollectableItem[ROW_NUMBER][COLUMN_NUMBER];
        cellCards1 = new Card[ROW_NUMBER][COLUMN_NUMBER];
        cellCards2 = new Card[ROW_NUMBER][COLUMN_NUMBER];
        cellFlags = new int[ROW_NUMBER][COLUMN_NUMBER];
    }

    public CellEffect[][] getCellEffects() {
        return cellEffects;
    }

    public CollectableItem[][] getCellItems() {
        return cellItems;
    }

    public Card[][] getCellCards1() {
        return cellCards1;
    }

    public Card[][] getCellCards2() {
        return cellCards2;
    }

    public int[][] getCellFlags() {
        return cellFlags;
    }

    public void setCellEffect(CellEffect cellEffects, int row, int column) {
        this.cellEffects[row][column] = cellEffects;
    }

    public void setCellItem(CollectableItem cellItems, int row, int column) {
        this.cellItems[row][column] = cellItems;
    }

    public void setCellCards1(Card[][] cellCards1) {
        this.cellCards1 = cellCards1;
    }

    public void setCellCards2(Card[][] cellCards2) {
        this.cellCards2 = cellCards2;
    }

    public void setCellFlags(int[][] cellFlags) {
        this.cellFlags = cellFlags;
    }

    public void removeCellFlag(int row, int column) {
        if (this.cellFlags[row][column] > 0)
            this.cellFlags[row][column]--;
    }

    public void addCellFlag(int row, int column) {
        this.cellFlags[row][column]++;
    }
}
