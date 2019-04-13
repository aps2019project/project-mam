package Model;

import java.util.ArrayList;

public class Item {
    private String name;
    private String desc;
    private ArrayList<Buff> buffs;

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
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
}
