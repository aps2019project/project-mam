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

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Name : ").append(getName()).append(" - Description : ").append(getDesc());
        info.append(" - Sell Cost : ").append(getPrice());
        return info.toString();
    }
}
