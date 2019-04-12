package Model;

public class Card {
    private int MP;
    private int price;
    private String name;
    private String desc;

    public Card(String name, int price, int MP, String desc) {
        this.MP = MP;
        this.price = price;
        this.name = name;
        this.desc = desc;
    }

    public int getMP() {
        return MP;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}