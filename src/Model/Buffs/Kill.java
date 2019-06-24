package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class Kill extends Buff {
    public Kill(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
        this.setKind("Kill");
    }

    public Kill(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("Kill");
    }

    @Override
    public Buff copy() {
        return new Kill(BuffType.KILL, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void doEffect() {
        getCard().decrementOfHp(100);
    }
}
