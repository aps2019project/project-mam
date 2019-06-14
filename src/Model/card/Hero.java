package Model.card;

import Model.Buffs.Buff;
import Model.enums.ImpactType;
import Model.enums.SPActivationTime;

import java.util.ArrayList;

public class Hero extends Card {

    private int AP;
    private int HP;
    private int TargetCommunity;
    private int cooldown;
    private final int BASE_COOL_DOWN;
    private ImpactType heroClass;


    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity,
                int MP, int cooldown) {
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        BASE_COOL_DOWN = cooldown;
        TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
        setType("hero");
    }

    public int getBASE_COOL_DOWN() {
        return BASE_COOL_DOWN;
    }

    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity,
                int MP, int cooldown, String desc, ArrayList<Buff> buffs){
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        this.TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        BASE_COOL_DOWN = cooldown;
        this.heroClass = heroClass;
        this.setDesc(desc);
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

    public ImpactType getCardClass() {
        return heroClass;
    }


    @Override
    public ArrayList<Buff> getSpecialPower() {
        return super.getSpecialPower();
    }

    @Override
    public SPActivationTime getSPActivationTime() {
        return super.getSPActivationTime();
    }

    /*public int getRow(){
        return row;
    }*/

    /*public int getColumn() {
        return column;
    }*/

    /*public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanAttack() {
        return canAttack;
    }*/

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void decrementOfCoolDown(int number){
        cooldown -= number;
    }

    /*public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }*/

   /* public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }*/

    /*public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }*/

    public void incrementOfHp(int number){
        this.HP += number;
    }

    public void decrementOfHp(int number){
        this.HP -= number;
        Buff.activeholyBuff(this);
    }

    public void incrementOfAp(int number){
        AP += number;
    }

    public void decrementOfAp(int number){
        AP -= number;
    }

    /*public boolean canCounterAttack(){
        return canCounterAttack;
    }*/

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Name : ").append(getName()).append(" - AP : ").append(getAP());
        info.append(" - HP : ").append(getHP()).append(" - Class : ").append(getCardClass());
        info.append(" - Special power : ").append(getDesc()).append(" - Sell Cost : ").append(getPrice());
        return info.toString();
    }

    @Override
    public String getInfoInShop() {
        StringBuilder info = new StringBuilder();
        info.append("Name : ").append(getName()).append("\nAP : ").append(getAP());
        info.append("\nHP : ").append(getHP()).append("\nClass : ").append(getCardClass());
        info.append("\nSpecial power : ").append(getDesc()).append("\nSell Cost : ").append(getPrice());
        return info.toString();
    }

    @Override
    public Card copyCard() {
        Card newCard = new Hero(this.getName(), this.getPrice(), this.HP, this.AP, this.heroClass, this.TargetCommunity,
                this.HP, this.cooldown, this.getDesc(), this.getSpecialPower());
        return newCard;
    }

    @Override
    public String getCardInfoInGame() {
        StringBuilder info = new StringBuilder();
        info.append("Hero:").append("\nName: ").append(this.getName());
        info.append("\nCost: ").append(this.getPrice()).append("\nDesc: ").append(this.getDesc());
        return info.toString();
    }

    @Override
    public String getImportantInfo() {
        StringBuilder info = new StringBuilder();
        info.append(getId()).append(" : ").append(getName()).append(",  health : ").append(getHP()).append(",  location : (").
                append(getRow()).append(", ").append(getColumn()).append("),  power : ").append(getAP());
        return info.toString();
    }
}





