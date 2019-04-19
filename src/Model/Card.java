package Model;

import java.util.ArrayList;

public class Card {
    private int MP;
    private int id;
    private int price;
    private String name;
    private String desc;
    private ArrayList<Buff> buffs = new ArrayList<>();

    Card() {
    }

    Card(String name, int price, int MP, String desc) {
        this.MP = MP;
        this.price = price;
        this.name = name;
        this.desc = desc;
    }

    Card(String name, int MP, int price) {
        this.MP = MP;
        this.price = price;
        this.name = name;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public String getCardType() {
        return null;
    }
    public String getInfo(){
        return null;
    }

    public int getRow(){
        return -1;
    }

    public int getColumn() {
        return -1;
    }

    public void setRow(int row) {
    }

    public void setColumn(int column) {
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public void addBuff(Buff buff, String sign) {
        buff.setSign(sign);
        buffs.add(buff);
    }

    public String getImportantInfo(){
        return "card";
    }
}
