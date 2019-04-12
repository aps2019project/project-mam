package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Minion extends Card{

    private int AP;
    private int HP;
    private int TargetCommunity;
    private ImpactType minionClass;
    private SPActivationTime SPActivationTime;
    private ArrayList<Buff> specialPower = new ArrayList<>();
    private ArrayList<Buff> buffs = new ArrayList<>();

    public Minion(String name, int price, int MP, int HP, int AP, ImpactType minionClass, int targetCommunity,
                  Model.SPActivationTime SPActivationTime, String desc) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
    }

    public Minion(String name, int price,int MP,int HP, int AP, int targetCommunity, ImpactType minionClass,
                  Model.SPActivationTime SPActivationTime) {
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
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

    public ImpactType getMinionClass() {
        return minionClass;
    }

    public Model.SPActivationTime getSPActivationTime() {
        return SPActivationTime;
    }

    public void addBuff(Buff buff, String sign){
        buff.setSign(sign);
        buffs.add(buff);
    }

    public void addBuffToSpecialPower(Buff buff){
        specialPower.add(buff);
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
