package Model;

import java.util.ArrayList;

public class Card {
    private int MP;
    private int id;
    private int price;
    private String name;
    private String desc;
    private ArrayList<Buff> buffs = new ArrayList<>();

    Card() {
    }

    Card(String name, int price, int MP, String desc) {
        this.MP = MP;
        this.price = price;
        this.name = name;
        this.desc = desc;
    }

    public void setBuffs(ArrayList<Buff> buffs) {
        this.buffs = buffs;
    }

    Card(String name, int MP, int price) {
        this.MP = MP;
        this.price = price;
        this.name = name;
    }
    public int getMP() {
        return MP;
    }

    public int getHP(){
        return -1;
    }

    public int getAP(){
        return -1;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public String getCardType() {
        return null;
    }
    public String getInfo(){
        return null;
    }

    public int getRow(){
        return -1;
    }

    public int getColumn() {
        return -1;
    }


    public ArrayList<Buff> getSpecialPower(){
        return null;
    }

    public boolean isCanMove() {
        return true;
    }

    public boolean isCanAttack() {
        return true;
    }

    public void setCanMove(boolean canMove) {
    }

    public void setCanAttack(boolean canAttack) {
    }

    public int getTargetCommunity() {
        return -1;
    }

    public ImpactType getCardClass() {
        return ImpactType._NULL;
    }

    public Model.SPActivationTime getSPActivationTime() {
        return SPActivationTime.NULL;
    }

    public void setRow(int row) {
    }

    public void setColumn(int column) {
    }

    public void incrementOfHp(int number){
    }

    public void decrementOfHp(int number){
    }

    public void incrementOfAp(int number){

    }

    public void decrementOfAp(int number){
    }

    public boolean canCounterAttack(){
        return true;
    }

    public void setCanCounterAttack(boolean canCounterAttack){

    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }


    public String getImportantInfo(){
        return "card";
    }


   public Card copyCard(){
       return null;
   }

   public String getCardInfoInGame(){
        return null;
   }
}
