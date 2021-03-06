package Model.card;

import Model.Buffs.Buff;
import Model.enums.ImpactType;

import java.util.ArrayList;

public class Minion extends Card {


    private int AP;
    private int HP;
    private int TargetCommunity;
    private ImpactType minionClass;
    private Model.enums.SPActivationTime SPActivationTime;


    public Minion(String name, int price, int MP, int HP, int AP, ImpactType minionClass, int targetCommunity,
                  String desc, Model.enums.SPActivationTime SPActivationTime, ArrayList<Buff> buffs) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
        type = "minion";
    }

    public Minion(String name, int price, int MP, int HP, int AP, int targetCommunity, ImpactType minionClass,
                  Model.enums.SPActivationTime SPActivationTime) {
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
        setType("minion");
    }

    @Override
    public String getCardType() {
        return "minion";
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Minion - Name : ").append(getName()).append(" ID : ").append(getId()).append(" - Class : ").append(getCardClass());
        info.append(" - AP : ").append(getAP()).append(" - HP : ").append(getHP()).append(" - MP : ").append(getMP());
        info.append(" - Special power : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice())
                .append(" - count : ").append(getCount());
        return info.toString();
    }

    @Override
    public String getInfoInShop() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Minion\nName : ").append(getName()).append("\nClass : ").append(getCardClass());
        info.append("\nAP : ").append(getAP()).append("     HP : ").append(getHP()).append("     MP : ").append(getMP());
        info.append("\nSpecial power : ").append(getDesc()).append("\nSell Cost : ").append(getPrice());
        return info.toString();
    }

    @Override
    public int getMP() {
        return super.getMP();
    }

    @Override
    public int getPrice() {
        return super.getPrice();
    }

    @Override
    public ArrayList<Buff> getSpecialPower() {
        return super.getSpecialPower();
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

    public ImpactType getCardClass() {
        return minionClass;
    }

    public Model.enums.SPActivationTime getSPActivationTime() {
        return SPActivationTime;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setTargetCommunity(int targetCommunity) {
        TargetCommunity = targetCommunity;
    }

    public void setMinionClass(ImpactType minionClass) {
        this.minionClass = minionClass;
    }

    public void setSPActivationTime(Model.enums.SPActivationTime SPActivationTime) {
        this.SPActivationTime = SPActivationTime;
    }

    @Override
    public Card copyCard() {
        Card newCard = new Minion(this.getName(), this.getPrice(), this.getMP(), this.getHP(),
                this.getAP(), this.getCardClass(), this.getTargetCommunity(),
                this.getDesc(), this.getSPActivationTime(), this.getSpecialPower());
        return newCard;

    }

    public String getImportantInfo() {
        StringBuilder info = new StringBuilder();
        info.append(getId()).append(" : ").append(getName()).append(",  health : ").append(getHP()).append(",  location : (").
                append(getRow()).append(", ").append(getColumn()).append("),  power : ").append(getAP());
        return info.toString();
    }

    @Override
    public String getCardInfoInGame() {
        StringBuilder info = new StringBuilder();
        info.append("Minion:").append("\nName: ").append(getName());
        info.append("\nHP: ").append(getHP()).append("  AP: ").append(getAP()).append("  MP: ").append(getMP());
        info.append("\nRange: ").append(getCardClass());
        if (haveComboAbility())
            info.append("\nCombo-ability: Yes");
        else info.append("\nCombo-ability: No");
        info.append("\nCost: ").append(getPrice()).append("\nDesc: ").append(getDesc());
        return info.toString();
    }

    public void incrementOfHp(int number) {
        this.HP += number;
    }

    public void decrementOfHp(int number) {
        this.HP -= number;
        Buff.activeholyBuff(this);
    }

    public void incrementOfAp(int number) {
        AP += number;
    }

    public void decrementOfAp(int number) {
        AP -= number;
    }

    public boolean haveComboAbility() {
        return getSPActivationTime() == Model.enums.SPActivationTime.COMBO;
    }
}
