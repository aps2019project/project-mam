package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Hero extends Card {
    private int AP;
    private int HP;
    private int TargetCommunity;
    private int cooldown;
    private ImpactType heroClass;
    private Spell specialPower;
    private ArrayList<Buff> buffs = new ArrayList<>();

    public Hero(String name, int price, int MP, String desc, int AP, int HP, int targetCommunity,
                int cooldown, ImpactType heroClass, Spell specialPower) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
        this.specialPower = specialPower;
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

    public void addBuff(Buff buff, String sign){
        buff.setSign(sign);
        buffs.add(buff);
    }

    public void updateBuffList(){
        Iterator itr = buffs.iterator();
        while (itr.hasNext()){
            Buff temp = (Buff) itr;
            if (temp.getTime() == 0)
                itr.remove();
        }
    }
}
