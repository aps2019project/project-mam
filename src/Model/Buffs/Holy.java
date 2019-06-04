package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class Holy extends Buff {
    public Holy(BuffType type, int remainTime, int buffPower, int activationTime,
                boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    public Buff copy(){
        return new Holy(BuffType.HOLY, this.getRemainTime(), this.getBuffPower(),
                this.getActivationTime(), this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void doEffect() {
        getCard().incrementOfHp(getBuffPower());
    }
}
