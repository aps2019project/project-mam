package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class Stun extends Buff {
    public Stun(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public Buff copy() {
        return new Stun(BuffType.STUN, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void doEffect() {
        getCard().setCanMove(false);
        getCard().setCanAttack(false);
    }
}
