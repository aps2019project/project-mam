package Model.Buffs;

import Model.*;
import com.sun.deploy.nativesandbox.NativeSandboxBroker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Buff {
    private static ArrayList<Buff> buffs;
    private BuffType type;
    private int remainTime;
    private int buffPower;
    private int activationTime;
    private boolean isStarted = false;
    private boolean isUsed = false;
    private boolean isContinous = false;
    private TargetCommunity targetCommunity;
    private Card card;

    public Buff(BuffType type, int time, int buffPower, Model.TargetCommunity targetCommunity) {
        this.type = type;
        this.remainTime = time;
        this.buffPower = buffPower;
        this.targetCommunity = targetCommunity;
    }

    public Buff(BuffType type, int buffPower, int activationTime,
                 boolean isContinous, TargetCommunity targetCommunity) {
        this.type = type;
        this.buffPower = buffPower;
        this.activationTime = activationTime;
        this.isContinous = isContinous;
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

    public void setBuffPower(int buffPower) {
        this.buffPower = buffPower;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public void setContinous(boolean continous) {
        isContinous = continous;
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

    public boolean isContinous() {
        return isContinous;
    }

    public int getActivationTime() {
        return activationTime;
    }

    public Buff copy(){
        return null;
    }

    public static void activeholyBuff(Card card) {
        if (buffs != null)
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
            case RANGED_HYBRID:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet()) {
                    if (entry.getValue().getCard().getCardClass() != ImpactType.MELEE) {
                        cells.add(entry.getValue());
                        break;
                    }
                }
                break;
            case ONE_ENEMY_FORCE_RANDOM:
                Random rand = new Random();
                cells.add(defenderTeam.get(rand.nextInt(defenderTeam.size())));
                break;
            case ONE_INSIDER_FORCE_RANDOM:
                Random random = new Random();
                cells.add(attackerTeam.get(random.nextInt(attackerTeam.size())));
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
        if (buffs != null)
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

    public void setGame(Game game) {
    }

    public void doEffect(){}
    public void doEffect(Cell cell){}
    public void addBuff(Cell cell){}
    public void removeBuff(){
        buffs.remove(this);
    }
}





