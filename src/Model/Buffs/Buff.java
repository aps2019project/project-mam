package Model.Buffs;

import Model.card.Card;
import Model.enums.BuffType;
import Model.enums.ImpactType;
import Model.enums.SPActivationTime;
import Model.enums.TargetCommunity;
import Model.game.Cell;
import Model.game.Game;

import java.util.*;

public class Buff {
    private transient static ArrayList<Buff> buffs = new ArrayList<>();
    private BuffType type;
    private int remainTime;
    private int buffPower;
    private String kind;
    private int activationTime;
    private boolean isStarted = false;
    private boolean isUsed = false;
    private boolean isContinous = false;
    private TargetCommunity targetCommunity;
    private transient Card card;
    private transient Cell cell;

    public Buff(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        kind = type.toString();
        this.type = type;
        this.remainTime = time;
        this.buffPower = buffPower;
        this.targetCommunity = targetCommunity;
        buffs = new ArrayList<>();
    }

    public Buff(BuffType type, int buffPower, int activationTime,
                boolean isContinous, TargetCommunity targetCommunity) {
        kind = type.toString();
        this.type = type;
        this.buffPower = buffPower;
        this.activationTime = activationTime;
        this.isContinous = isContinous;
        this.targetCommunity = targetCommunity;
        buffs = new ArrayList<>();
    }

    public Buff(BuffType type, int remainTime, int buffPower, int activationTime,
                boolean isStarted, TargetCommunity targetCommunity) {
        kind = type.toString();
        this.type = type;
        this.remainTime = remainTime;
        this.buffPower = buffPower;
        this.activationTime = activationTime;
        this.isStarted = isStarted;
        this.targetCommunity = targetCommunity;
        buffs = new ArrayList<>();
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public static void addBuff(Buff buff) {
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

    public Buff copy() {
        return null;
    }

    public static void activeholyBuff(Card card) {
        if (buffs != null)
            for (Buff buff : buffs) {
                if (buff.getCard().equals(card) && buff instanceof Holy)
                    buff.doEffect();
            }
    }

    public ArrayList<Card> getMinionsSPTarget(Card attacker, Card defender,
                                              HashMap<Integer, Cell> attackerTeam,
                                              HashMap<Integer, Cell> defenderTeam,
                                              Model.game.Map map) {

        ArrayList<Card> cards = new ArrayList<>();

        switch (targetCommunity) {
            case OWN:
                cards.add(attacker);
                break;
            case ONE_ENEMY_FORCE:
                if (defenderTeam.containsKey(defender.getId()))
                    cards.add(defender);
                break;
            case ONE_INSIDER_FORCE:
                if (attackerTeam.containsKey(defender.getId()))
                    cards.add(defender);
                break;
            case ALL_ENEMY_FORCES:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    cards.add(entry.getValue().getCard());
                break;
            case ALL_INSIDER_FORCES:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet())
                    cards.add(entry.getValue().getCard());
                break;
            case INSIDER_HERO:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet())
                    if (entry.getValue().getCard().getCardType().equals("hero"))
                        cards.add(entry.getValue().getCard());
                break;
            case ENEMY_HERO:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet())
                    if (entry.getValue().getCard().getCardType().equals("hero"))
                        cards.add(entry.getValue().getCard());
                break;
            case ALL_ENEMY_FORCES_CLOSE:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet()) {
                    if (map.getManhatanDistance(entry.getValue(), attackerTeam.get(attacker.getId())) == 1 ||
                            (map.getManhatanDistance(entry.getValue(), attackerTeam.get(attacker.getId())) == 2 &&
                                    entry.getValue().getRow() != attacker.getRow() &&
                                    entry.getValue().getColumn() != attacker.getColumn())) {
                        cards.add(entry.getValue().getCard());
                    }
                }
                break;
            case OWN_AND_INSIDER_FORCES_CLOSE:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet()) {
                    if (map.getManhatanDistance(entry.getValue(), attackerTeam.get(attacker.getId())) == 1 ||
                            (map.getManhatanDistance(entry.getValue(), attackerTeam.get(attacker.getId())) == 2 &&
                                    entry.getValue().getRow() != attacker.getRow() &&
                                    entry.getValue().getColumn() != attacker.getColumn())) {
                        cards.add(entry.getValue().getCard());
                    }
                }
                cards.add(attacker);
        }


        return cards;
    }

    public ArrayList<Cell> getSpellTarget(Cell defender,
                                          HashMap<Integer, Cell> attackerTeam,
                                          HashMap<Integer, Cell> defenderTeam,
                                          Model.game.Map map) {
        ArrayList<Cell> cells = new ArrayList<>();
        switch (targetCommunity) {
            case ONE_ENEMY_FORCE:
                /*if (defenderTeam.containsValue(defender))
                    cells.add(defender);*/
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet()) {
                    if (entry.getValue().getColumn() == defender.getColumn() &&
                            entry.getValue().getRow() == defender.getRow()) {
                        cells.add(defender);
                        break;
                    }
                }
                break;
            case ONE_INSIDER_FORCE:
                /*if (attackerTeam.containsValue(defender))
                    cells.add(defender);*/
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet()) {
                    if (entry.getValue().getColumn() == defender.getColumn() &&
                            entry.getValue().getRow() == defender.getRow()) {
                        cells.add(defender);
                        break;
                    }
                }
                break;
            case ALL_ENEMY_FORCES:
                for (Map.Entry<Integer, Cell> entry : defenderTeam.entrySet()) {
                    cells.add(entry.getValue());
                }
                break;
            case ALL_INSIDER_FORCES:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet()) {
                    cells.add(entry.getValue());
                }
                break;
            case INSIDER_HERO:
                for (Map.Entry<Integer, Cell> entry : attackerTeam.entrySet())
                    if (entry.getValue().getCard().getCardType().equals("hero"))
                        cells.add(entry.getValue());
                break;
            case TWO_IN_TWO:
                cells.add(map.getCells()[defender.getRow()][defender.getColumn()]);
                if (defender.getColumn() + 1 < 9)
                    cells.add(map.getCells()[defender.getRow()][defender.getColumn() + 1]);
                if (defender.getRow() + 1 < 5)
                    cells.add(map.getCells()[defender.getRow() + 1][defender.getRow()]);
                if (defender.getColumn() + 1 < 9 && defender.getRow() + 1 < 5)
                    cells.add(map.getCells()[defender.getRow() + 1][defender.getColumn() + 1]);
                break;
            case THREE_IN_THREE:
                cells.add(map.getCells()[defender.getRow()][defender.getColumn()]);

                if (defender.getColumn() + 1 < 9)
                    cells.add(map.getCells()[defender.getRow()][defender.getColumn() + 1]);

                if (defender.getRow() + 1 < 5)
                    cells.add(map.getCells()[defender.getRow() + 1][defender.getRow()]);

                if (defender.getColumn() + 1 < 9 && defender.getRow() + 1 < 5)
                    cells.add(map.getCells()[defender.getRow() + 1][defender.getColumn() + 1]);

                if (defender.getColumn() + 1 < 9 && defender.getRow() + 2 < 5)
                    cells.add(map.getCells()[defender.getRow() + 2][defender.getColumn() + 1]);

                if (defender.getColumn() + 2 < 9 && defender.getRow() + 1 < 5)
                    cells.add(map.getCells()[defender.getRow() + 1][defender.getColumn() + 2]);

                if (defender.getColumn() + 2 < 9 && defender.getRow() + 2 < 5)
                    cells.add(map.getCells()[defender.getRow() + 2][defender.getColumn() + 2]);

                if (defender.getColumn() + 2 < 9 && defender.getRow() < 5)
                    cells.add(map.getCells()[defender.getRow()][defender.getColumn() + 2]);

                if (defender.getColumn() < 9 && defender.getRow() + 2 < 5)
                    cells.add(map.getCells()[defender.getRow() + 2][defender.getColumn()]);
                break;
            case ONE_ENEMY_FORCE_RANDOM:
                Random rand = new Random();
                cells.add(defenderTeam.get(rand.nextInt(defenderTeam.size() - 1)));
                break;
        }
        return cells;
    }

    public ArrayList<Cell> getSpecialPowerTargetCells(Cell attacker, Cell defender,
                                                      HashMap<Integer, Cell> attackerTeam,
                                                      HashMap<Integer, Cell> defenderTeam,
                                                      Model.game.Map map) {
        ArrayList<Cell> cells = new ArrayList<>();
        switch (targetCommunity) {
            case OWN:
                cells.add(attacker);
                break;
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
                                    .getRow() && entry.getValue().getColumn() != attacker.getColumn())) {
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

    public static void updateBuffs() {
        if (buffs != null) {
            Iterator<Buff> iterator = buffs.iterator();
            while (iterator.hasNext()) {
                Buff next = iterator.next();
                next.setUsed(false);
                if (next.getKind().equals("Poison"))
                    next.setUsed(true);
                next.decrementOfTime();
                if (next.getTime() == 0) {
                    next.removeBuff();
                    iterator.remove();
                }
            }
        }
    }

    public static void refreshIsUsed() {
        if (buffs != null)
            for (Buff buff : buffs) {
                if (!buff.getKind().equals("Poison"))
                    buff.setUsed(false);
            }
    }

    public static void refreshBuffs() {
        if (buffs != null)
            for (Buff buff : buffs) {
                if (!buff.isUsed && buff.isStarted()) {
                    if (buff.getCard() != null)
                        buff.doEffect();
                    else buff.doEffect(buff.getCell());
                    buff.isUsed = true;
                }
            }
    }

    public static void activePassiveBuff(HashMap<Integer, Cell> cells) {
        for (java.util.Map.Entry<Integer, Cell> entry : cells.entrySet()) {
            if (entry.getValue().getCard().getSPActivationTime() == SPActivationTime.PASSIVE) {
                for (Buff buff : entry.getValue().getCard().getSpecialPower()) {
                    Buff newBuff = buff.copy();
                    newBuff.setCard(entry.getValue().getCard());
                    newBuff.setStarted(true);
                    Buff.addBuff(newBuff);
                }
            }
        }
    }

    public void setGame(Game game) {
    }

    public void addBuff(Cell cell) {
    }

    public void doEffect() {
    }

    public void doEffect(Cell cell) {
    }

    public void removeBuff() {
        //buffs.remove(this);
    }
}





