package Model;

public class UsableItem extends Item {
    private int price;

    public UsableItem(String name, int price, String desc) {
        super(name, desc);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
