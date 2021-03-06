package Model.item;

import Model.Buffs.Buff;
import Model.enums.SPActivationTime;

import java.util.ArrayList;

public class Item {
    private String name;
    private String desc;
    private int id;
    private ArrayList<Buff> buffs = new ArrayList<>();
    private String imageAddress;
    private String actionAdress;
    private int count = 5;

    public Item(){}

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getActionAdress() {
        return actionAdress;
    }

    public void setActionAdress(String actionAdress) {
        this.actionAdress = actionAdress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getPrice(){
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SPActivationTime getSpActivationTime() {
        return null;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public void addBuff(Buff buff){
        buffs.add(buff);
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("name : ").append(name).append(" id : ").append(id).append(" desc : ").append(desc).append(" count : ").append(count);
        return info.toString();
    }

    public String getInfoInShop(){
        return toString();
    }
}





