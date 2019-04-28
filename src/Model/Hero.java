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
    private boolean canMove = true;
    private boolean canAttack = true;
    private boolean canCounterAttack = true;

    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity,
                int MP, int cooldown) {
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
    }

    public Hero(String name, int price, int HP, int AP, ImpactType heroClass, int targetCommunity,
                int MP, int cooldown, String desc, ArrayList<Buff> buffs){
        super(name, MP, price);
        this.AP = AP;
        this.HP = HP;
        this.TargetCommunity = targetCommunity;
        this.cooldown = cooldown;
        this.heroClass = heroClass;
        this.setDesc(desc);
        this.specialPower = buffs;
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

    public void addBuffToSpecialPower(Buff buff){
        specialPower.add(buff);
    }

    public int getRow(){
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

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setCanCounterAttack(boolean canCounterAttack) {
        this.canCounterAttack = canCounterAttack;
    }

    public void incrementOfHp(int number){
        this.HP -= number;
    }

    public void decrementOfHp(int number){
        this.HP += number;
    }

    public void incrementOfAp(int number){
        AP += number;
    }

    public void decrementOfAp(int number){
        AP -= number;
    }

    public boolean canCounterAttack(){
        return canCounterAttack;
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

    @Override
    public Card copyCard() {
        Card newCard = new Hero(this.getName(), this.getPrice(), this.HP, this.AP, this.heroClass, this.TargetCommunity,
                this.HP, this.cooldown, this.getDesc(), this.specialPower);
        return newCard;
    }

    @Override
    public String getCardInfoInGame() {
        StringBuilder info = new StringBuilder();
        info.append("Hero:").append("\nName: ").append(this.getName());
        info.append("\nCost: ").append(this.getPrice()).append("\nDesc: ").append(this.getDesc());
        return info.toString();
    }
}





