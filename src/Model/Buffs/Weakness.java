package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class Weakness extends Buff {
    public Weakness(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                    TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public Buff copy() {
        if (getType() == BuffType.ATTACK_WEAKNESS)
        return new Weakness(BuffType.ATTACK_WEAKNESS, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
        else
            return new Poison(BuffType.HEALTH_WEAKNESS, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                    this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void doEffect() {
        if (getType() == BuffType.ATTACK_WEAKNESS)
            getCard().decrementOfAp(getBuffPower());
        else
            getCard().decrementOfHp(getBuffPower());
    }

    @Override
    public void removeBuff() {
        if (getType() == BuffType.ATTACK_WEAKNESS)
            getCard().incrementOfAp(getBuffPower());
        else
            getCard().incrementOfHp(getBuffPower());
        getBuffs().remove(this);
    }
}