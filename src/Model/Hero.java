package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Hero extends Card {
    private int row;
    private int column;
    private int AP;
    private int HP;
    private int TargetCommunity;
    private int cooldown;
    private ImpactType heroClass;
    private ArrayList<Buff> specialPower = new ArrayList<>();
    private int flagCount;

    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity,
                int MP, int cooldown) {
        super(name, price, MP);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
    }

    @Override
    public String getCardType() {
        return "hero";
    }

    public int getAP() {
        return AP;
    }

    public int getHP() {
        return HP;
    }

    public int getTargetCommunity() {
        return TargetCommunity;
    }

    public int getCooldown() {
        return cooldown;
    }

    public ImpactType getHeroClass() {
        return heroClass;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public void setFlagCount(int flagCount) {
        this.flagCount = flagCount;
    }

    public void addBuffToSpecialPower(Buff buff){
        specialPower.add(buff);
    }

    public int getRow(){
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void updateBuffList(){
        Iterator itr = getBuffs().iterator();
        while (itr.hasNext()){
            Buff temp = (Buff) itr;
            if (temp.getTime() == 0)
                itr.remove();
        }
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Name : ").append(getName()).append(" - AP : ").append(getAP());
        info.append(" - HP : ").append(getHP()).append(" - Class : ").append(getHeroClass());
        info.append(" - Special power : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice());
        return info.toString();
    }
}





