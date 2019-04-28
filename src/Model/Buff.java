package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static Model.TargetCommunity.*;

public class Buff {
    private BuffType type;
    private int time;
    private int buffPower;
    private boolean isStarted;
    private TargetCommunity targetCommunity;

    public Buff(BuffType type, int time, int buffPower, Model.TargetCommunity targetCommunity) {
        this.type = type;
        this.time = time;
        this.buffPower = buffPower;
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
        return time;
    }

    public int getBuffPower() {
        return buffPower;
    }

    public TargetCommunity getTargetCommunity() {
        return targetCommunity;
    }

    public void decrementOfTime() {
        time--;
    }

    //-----------------------------effect-----------------------------

    public void oneEnemyForce(Card card) {
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
        }
    }

    public void oneInsiderForce(Card card) {
        switch (type) {
            case ATTACK_POWER:
                card.incrementOfAp(buffPower);
                break;
            case HEALTH_POWER:
                card.incrementOfHp(buffPower);
                break;
        }
    }

    public void holyBuff(Card card) {
        card.incrementOfHp(buffPower);
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
        }
        return cells;
    }

}





