package Model;

import java.util.ArrayList;

public class Spell extends Card{
    private TargetCommunity TargetCommunity;
    ArrayList<Buff> buffs = new ArrayList<>();

    public Spell(String name, int price, int MP, Model.TargetCommunity targetCommunity, String desc) {
        super(name, price, MP, desc);
        TargetCommunity = targetCommunity;
    }

    public void addBuff(Buff buff){
        this.buffs.add(buff);
    }

    public Model.TargetCommunity getTargetCommunity() {
        return TargetCommunity;
    }
}
