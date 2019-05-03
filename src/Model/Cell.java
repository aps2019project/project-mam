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

    public Card getCard() {
        return card;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCellEffect(CellEffect cellEffect) {
        this.cellEffect = cellEffect;
    }

    public void setCollectableItem(CollectableItem collectableItem) {
        this.collectableItem = collectableItem;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean haveCollectableItem(){
        return collectableItem != null;
    }

    public boolean isCellEmpty(){
        return card != null;
    }

    public void incrementOfFlag(int number){
        flagCount += number;
    }


}
