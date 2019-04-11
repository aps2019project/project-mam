package Model;

public class Map {
    private CellEffect[][] cellEffects;
    private CollectableItem[][] cellItems;
    private Card[][] cellCards;
    private int[][] cellFlags;

    public Map() {
        cellEffects = new CellEffect[5][9];
        cellItems = new CollectableItem[5][9];
        cellCards = new Card[5][9];
        cellFlags = new int[5][9];
    }

    public CellEffect[][] getCellEffects() {
        return cellEffects;
    }

    public CollectableItem[][] getCellItems() {
        return cellItems;
    }

    public Card[][] getCellCards() {
        return cellCards;
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

    public void setCellCards(Card[][] cellCards) {
        this.cellCards = cellCards;
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
