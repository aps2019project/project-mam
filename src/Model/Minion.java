package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Minion extends Card{

    private int row;
    private int column;
    private int AP;
    private int HP;
    private int TargetCommunity;
    private int flagCount;
    private ImpactType minionClass;
    private SPActivationTime SPActivationTime;
    private ArrayList<Buff> specialPower = new ArrayList<>();


    public Minion(String name, int price, int MP, int HP, int AP, ImpactType minionClass, int targetCommunity,
                  Model.SPActivationTime SPActivationTime, String desc) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
        flagCount = 0;
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

    @Override
    public String getCardType() {
        return "minion";
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

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Minion - Name : ").append(getName()).append(" - Class : ").append(getMinionClass());
        info.append(" - AP : ").append(getAP()).append(" - HP : ").append(getHP()).append(" - MP : ").append(getMP());
        info.append(" - Special power : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice());
        return info.toString();
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



    public void addBuffToSpecialPower(Buff buff){
        specialPower.add(buff);
    }

    public void updateBuffList(){
        Iterator itr = getBuffs().iterator();
        while (itr.hasNext()){
            Buff temp = (Buff) itr;
            if (temp.getTime() == 0)
                itr.remove();
        }
    }
}
