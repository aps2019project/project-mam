package Model.Buffs;

import Model.enums.BuffType;
import Model.game.Cell;
import Model.enums.TargetCommunity;

public class Power extends Buff {
    public Power(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                 TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public void doEffect(Cell cell) {
        if (getType() == BuffType.ATTACK_POWER)
            getCard().incrementOfAp(getBuffPower());
        else
            getCard().incrementOfHp(getBuffPower());
    }

    public Buff copy(){
        if (getType() == BuffType.ATTACK_POWER)
        return new Power(BuffType.ATTACK_POWER, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
        else
            return new Power(BuffType.HEALTH_POWER, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                    this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void removeBuff() {
        if (getType() == BuffType.ATTACK_POWER)
            getCard().decrementOfAp(getBuffPower());
        else
            getCard().decrementOfHp(getBuffPower());
        getBuffs().remove(this);
    }
}
