package Model.Buffs;

import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Model.TargetCommunity.*;

public class Buff {
    private static ArrayList<Buff> buffs;
    private BuffType type;
    private int remainTime;
    private int buffPower;
    private int activationTime;
    private boolean isStarted = false;
    private boolean isUsed = false;
    private TargetCommunity targetCommunity;
    private Card card;

    public Buff(BuffType type, int time, int buffPower, Model.TargetCommunity targetCommunity) {
        this.type = type;
        this.remainTime = time;
        this.buffPower = buffPower;
        this.targetCommunity = targetCommunity;
    }


    public Buff(BuffType type, int remainTime, int buffPower, int activationTime,
                boolean isStarted, TargetCommunity targetCommunity) {
        this.type = type;
        this.remainTime = remainTime;
        this.buffPower = buffPower;
        this.activationTime = activationTime;
        this.isStarted = isStarted;
        this.targetCommunity = targetCommunity;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public BuffType getType() {
        return type;
    }

    public int getTime() {
        return remainTime;
    }

    public int getBuffPower() {
        return buffPower;
    }

    public TargetCommunity getTargetCommunity() {
        return targetCommunity;
    }

    public void decrementOfTime() {
        remainTime--;
    }

    public static void addBuff(Buff buff){
        buffs.add(buff);
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public static ArrayList<Buff> getBuffs() {
        return buffs;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public int getActivationTime() {
        return activationTime;
    }

    public Buff copy(){
        return null;
    }



    //-----------------------------effect-----------------------------

    public void doEffect(Card card) {
        switch (type) {
            case POISON:
                card.decrementOfHp(buffPower);
                break;
            case ATTACK_WEAKNESS:
                card.decrementOfAp(buffPower);
                break;
            case HEALTH_WEAKNESS:
                card.decrementOfHp(buffPower);
            case STUN:
                card.setCanMove(false);
                card.setCanAttack(false);
                break;
            case DISARM:
                card.setCanCounterAttack(false);
                break;
            case ATTACK_POWER:
                card.incrementOfAp(buffPower);
                break;
            case HEALTH_POWER:
                card.incrementOfHp(buffPower);
                break;
        }
    }

    public static void activeholyBuff(Card card) {
        for (Buff buff : buffs) {
            if (buff.getCard().equals(card) && buff instanceof Holy)
                buff.doEffect();
        }
    }

    public static void activeStunBuff(Card card){
        for (Buff buff : buffs) {
            if (buff.getCard().equals(card) && buff instanceof Stun){
                buff.doEffect();
            }
        }
    }

    public static void activeDisarmBuff(Card card){
        for (Buff buff : buffs) {
            if (buff.getCard().equals(card) && buff instanceof  Disarm)
                buff.doEffect();
        }
    }

    public ArrayList<Cell> getSpecialPowerTargetCells(Cell attacker, Cell defender,
                                                      HashMap<Integer, Cell> attackerTeam,
                                                      HashMap<Integer, Cell> defenderTeam,
                                                      Model.Map map) {
        ArrayList<Cell> cells = null;
        switch (targetCommunity) {
            case ONE_ENEMY_FORCE:
                cells.add(defender);
                break;
            case ONE_INSIDER_FORCE:
                cells.add(attacker);
                break;
            case ALL_ENEMY_FORCES:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    cells.add(entry.getValue());
                break;
            case ALL_INSIDER_FORCES:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet())
                    cells.add(entry.getValue());
                break;
            case TWO_IN_TWO:
                cells.add(map.getCells()[attacker.getRow()][attacker.getColumn()]);
                if (attacker.getColumn() < 9)
                    cells.add(map.getCells()[attacker.getRow()][attacker.getColumn() + 1]);
                if (attacker.getRow() < 5)
                    cells.add(map.getCells()[attacker.getRow() + 1][attacker.getRow()]);
                if (attacker.getColumn() < 9 && attacker.getRow() < 5)
                    cells.add(map.getCells()[attacker.getRow() + 1][attacker.getColumn() + 1]);
                break;
            case INSIDER_HERO:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet())
                    if (entry.getValue().getCard().getCardType().equals("hero"))
                        cells.add(entry.getValue());
                break;
            case ENEMY_HERO:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    if (entry.getValue().getCard().getCardType().equals("hero"))
                        cells.add(entry.getValue());
                break;
            case ALL_ENEMY_FORCES_IN_ROW:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    if (entry.getValue().getRow() == attacker.getRow())
                        cells.add(entry.getValue());
                    break;
            case ALL_ENEMY_FORCES_IN_COLUMN:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    if (entry.getValue().getColumn() == attacker.getColumn())
                        cells.add(entry.getValue());
                    break;
            case ALL_ENEMY_FORCES_CLOSE:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet()) {
                    if (map.getManhatanDistance(entry.getValue(), attacker) == 1 ||
                            (map.getManhatanDistance(entry.getValue(), attacker) == 2 && entry.getValue().getRow() != attacker
                            .getRow() && entry.getValue().getColumn() != attacker.getColumn())){
                        cells.add(entry.getValue());
                    }
                }
                break;
        }
        return cells;
    }

    public static void doEffects(){
        for (Buff buff : buffs) {
            if (buff.isUsed() && buff.isStarted()){
                buff.doEffect();
                buff.setUsed(true);
            }
        }
    }
    public static void updateBuffs(){
        for (Buff buff : buffs) {
            buff.setUsed(false);
            buff.decrementOfTime();
            if (buff.getTime() == 0){
                buff.removeBuff();
            }
        }
    }

    public static void activePassiveBuff(HashMap<Integer, Cell> cells){
        for (java.util.Map.Entry<Integer, Cell> entry : cells.entrySet()) {
            if (entry.getValue().getCard().getSPActivationTime() == SPActivationTime.PASSIVE){
                for (Buff buff : entry.getValue().getCard().getSpecialPower()) {
                    Buff newBuff = buff.copy();
                    newBuff.setCard(entry.getValue().getCard());
                    Buff.addBuff(newBuff);
                }
            }
        }
    }

    public void doEffect(){}
    public void doEffect(Card card){}
    public void doEffect(Cell cell){}
    public void addBuff(Cell cell){}
    public void removeBuff(){
        buffs.remove(this);
    }
}





