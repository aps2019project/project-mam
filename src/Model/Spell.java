package Model;

import java.util.ArrayList;

public class Spell extends Card{

    public Spell(String name, int price, int MP, String desc) {
        super(name, price, MP, desc);
    }

    public Spell(){}

    public Spell(String name, int price, int MP, ArrayList<Buff> buffs) {
        super(name, price, MP);
        //this.buffs = buffs;
    }

    public Spell(String name, int price,int MP) {
        super(name, MP, price);
    }

    @Override
    public String getCardType() {
        return "spell";
    }
}
