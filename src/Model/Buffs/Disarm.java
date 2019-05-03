package Model.Buffs;

import Model.BuffType;
import Model.TargetCommunity;

public class Disarm extends Buff {
    public Disarm(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public void doEffect() {
        getCard().setCanCounterAttack(false);
    }

    public Buff copy(){
        return new Disarm(BuffType.DISARM, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(), this.isStarted(), this.getTargetCommunity());
    }
}
