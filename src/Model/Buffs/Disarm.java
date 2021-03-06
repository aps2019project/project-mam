package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class Disarm extends Buff {
    public Disarm(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
        this.setKind("Disarm");
    }

    public Disarm(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("Disarm");
    }

    @Override
    public void doEffect() {
        getCard().setCanCounterAttack(false);
    }

    public Buff copy(){
        return new Disarm(BuffType.DISARM, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(), this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void removeBuff() {
        getCard().setCanCounterAttack(true);
        super.removeBuff();
    }
}
