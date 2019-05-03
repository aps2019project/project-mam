package Model;

import Model.Buffs.Buff;

import java.util.ArrayList;

public class Item {
    private String name;
    private String desc;
    private int id;
    private ArrayList<Buff> buffs;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public void addBuff(Buff buff){
        buffs.add(buff);
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("name : ").append(name).append(" id : ").append(id).append(" desc : ").append(desc);
        return info.toString();
    }
}





