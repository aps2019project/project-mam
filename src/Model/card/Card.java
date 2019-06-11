package Model.card;

import Model.Buffs.Buff;
import Model.enums.ImpactType;
import Model.enums.SPActivationTime;

import java.util.ArrayList;

public class Card {
    private int MP;
    private int id;
    private int price;
    private String name;
    private String desc;
    private ArrayList<Buff> specialPower = new ArrayList<>();
    private String type;

    private boolean selected = false;

    Card() {
    }

    Card(String name, int price, int MP, String desc) {
        this.MP = MP;
        this.price = price;
        this.name = name;
        this.desc = desc;
    }

    public void setSpecialPower(ArrayList<Buff> specialPower) {
        this.specialPower = specialPower;
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

    public void setMP(int MP) {
        this.MP = MP;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardType() {
        return type;
    }
    public  String getInfo(){
        return toString();
    }

    public String getInfoInShop(){
        return toString();
    }

    public int getRow(){
        return -1;
    }

    public int getColumn() {
        return -1;
    }


    public ArrayList<Buff> getSpecialPower(){
        return specialPower;
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

    public SPActivationTime getSPActivationTime() {
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

    public void addBuff(Buff buff){
        specialPower.add(buff);
    }

    public String getImportantInfo(){
        return "card";
    }


   public Card copyCard(){
       System.out.println("hell");
       return null;
   }

   public String getCardInfoInGame(){
        return null;
   }

    public int getBASE_COOL_DOWN() {
        return -1;
    }

    public int getCooldown(){
        return -1;
    }

    public void setCooldown(int cooldown) {
    }

    public void decrementOfCoolDown(int number){
    }

    @Override
    public String toString() {
        return "Card{" +
                "MP=" + MP +
                ", id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", specialPower=" + specialPower +
                ", selected=" + selected +
                '}';
    }
}
