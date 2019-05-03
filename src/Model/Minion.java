package Model;

import Model.Buffs.Buff;

import java.util.ArrayList;
import java.util.Iterator;

public class Minion extends Card {

    private int row;
    private int column;
    private int AP;
    private int HP;
    private int TargetCommunity;
    private ImpactType minionClass;
    private SPActivationTime SPActivationTime;
    private boolean canMove = true;
    private boolean canAttack = true;
    private boolean canCounterAttack = true;


    public Minion(String name, int price, int MP, int HP, int AP, ImpactType minionClass, int targetCommunity,
                  String desc, Model.SPActivationTime SPActivationTime, ArrayList<Buff> buffs) {
        super(name, price, MP, desc);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.minionClass = minionClass;
        this.SPActivationTime = SPActivationTime;
    }

    public Minion(String name, int price, int MP, int HP, int AP, int targetCommunity, ImpactType minionClass,
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public boolean canCounterAttack() {
        return canCounterAttack;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Type : Minion - Name : ").append(getName()).append(" ID : ").append(getId()).append(" - Class : ").append(getCardClass());
        info.append(" - AP : ").append(getAP()).append(" - HP : ").append(getHP()).append(" - MP : ").append(getMP());
        info.append(" - Special power : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice());
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

    public Model.SPActivationTime getSPActivationTime() {
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

    public void setSPActivationTime(Model.SPActivationTime SPActivationTime) {
        this.SPActivationTime = SPActivationTime;
    }

    @Override
    public Card copyCard() {
        Card newCard = new Minion(this.getName(), this.getPrice(), this.getMP(), this.getHP(),
                this.getAP(), this.getCardClass(), this.getTargetCommunity(),
                this.getDesc(), this.getSPActivationTime(), this.getSpecialPower());
        return newCard;

    }
    public String getImportantInfo(){
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
        return getSPActivationTime() == Model.SPActivationTime.COMBO;
    }
}
