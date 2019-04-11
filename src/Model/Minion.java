package Model;

public class Minion extends Card{

    private int AP;
    private int HP;
    private int TargetCommunity;
    private ImpactType minionClass;
    private SPActivationTime SPActivationTime;
    //private Spell specialPower;

    public Minion(String name, int price, int MP, int HP, int AP, ImpactType minionClass, int targetCommunity, Model.SPActivationTime SPActivationTime, String desc) {
        super(name, price, MP, desc);
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
}
