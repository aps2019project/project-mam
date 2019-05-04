package Model;

public class UsableItem extends Item {
    private int price;
    private SPActivationTime spActivationTime;

    public UsableItem(String name, String desc, int price, SPActivationTime spActivationTime) {
        super(name, desc);
        this.price = price;
        this.spActivationTime = spActivationTime;
    }

    public UsableItem(String name, int price, String desc) {
        super(name, desc);
        this.price = price;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public SPActivationTime getSpActivationTime() {
        return spActivationTime;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Name : ").append(getName()).append(" - Description : ").append(getDesc());
        info.append(" - Sell Cost : ").append(getPrice());
        return info.toString();
    }
}
