package Model;

import java.util.HashMap;
import java.util.Map;

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

    public void decrementOfTime() {
        time--;
    }

    //-----------------------------effect-----------------------------

    public void oneEnemyForce(Card card){
        switch (type){
            case POSION:
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

    public void oneInsiderForce(Card card){
        switch (type){
            case ATTACK_POWER:
                card.incrementOfAp(buffPower);
                break;
            case HEALTH_POWER:
                card.incrementOfHp(buffPower);
                break;
        }
    }

    public void holyBuff(Card card){
        card.incrementOfHp(buffPower);
    }


}
