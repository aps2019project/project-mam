package Model;

public class Cell {
    private int row;
    private int column;
    private int flagCount = 0;
    private CellEffect cellEffect;
    private CollectableItem collectableItem;
    private Card card;

    public Cell(int row, int column, int flagCount, CellEffect cellEffect, CollectableItem collectableItem) {
        this.row = row;
        this.column = column;
        this.flagCount = flagCount;
        this.cellEffect = cellEffect;
        this.collectableItem = collectableItem;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public CellEffect getCellEffect() {
        return cellEffect;
    }

    public CollectableItem getCollectableItem() {
        return collectableItem;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public void setCellEffect(CellEffect cellEffect) {
        this.cellEffect = cellEffect;
    }

    public void setCollectableItem(CollectableItem collectableItem) {
        this.collectableItem = collectableItem;
    }

    public boolean haveCollectableItem(){
        return false;
    }
}
