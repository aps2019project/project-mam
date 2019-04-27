package Model;

import java.util.ArrayList;
import java.util.Iterator;

public class Minion extends Card{

    private int row;
    private int column;
    private int AP;
    private int HP;
    private int TargetCommunity;
    private ImpactType minionClass;
    private SPActivationTime SPActivationTime;
    private ArrayList<Buff> specialPower = new ArrayList<>();
    private boolean canMove = true;
    private boolean canAttack = true;
    private boolean canCounterAttack = true;


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

    public boolean isCanMove() {
        return canMove;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public boolean canCounterAttack(){
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

    @Override
    public Card copyCard(Card card) {
        Card newCard = new Minion(card.getName(), card.getPrice(), card.getMP(), card.getHP(),
                card.getAP(), card.getTargetCommunity(), card.getMinionClass(),
                card.getSPActivationTime());
        return newCard;

    }

    public String getImportantInfo(){
        StringBuilder info = new StringBuilder();
        info.append(getId()).append(" : ").append(getName()).append(",  health : ").append(getHP()).append(",  location : (").
                append(getRow()).append(", ").append(getColumn()).append("),  power : ").append(getAP());
        return info.toString();
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
}
