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

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Spell - Name : ").append(getName()).append(" - MP : ").append(getMP());
        info.append(" - Description : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice());
        return info.toString();
    }
}
