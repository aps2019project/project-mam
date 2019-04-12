package Model;

import java.util.ArrayList;

public class Card {
    private int MP;
    private int price;
    private String name;
    private String desc;
    private ArrayList<Buff> buffs = new ArrayList<>();

    Card(){}

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

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public String getInfo(){
        return null;
    }

    public void addBuff(Buff buff){
        buffs.add(buff);
    }

    public void addBuff(Buff buff, String sign){
        buff.setSign(sign);
        buffs.add(buff);
    }
}
