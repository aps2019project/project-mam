package Model.Buffs;

import Model.BuffType;
import Model.Cell;
import Model.TargetCommunity;

public class DestroyPositives extends Buff {
    public DestroyPositives(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                            TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public void doEffect() {
        for (Buff buff : Buff.getBuffs()) {
            if (buff.getCard().equals(getCard()) && !buff.isContinous())
                buff.removeBuff();
        }
    }

    @Override
    public Buff copy() {
        return new DestroyPositives(BuffType.REMOVE_ENEMIES_BUFFS, this.getRemainTime(), this.getBuffPower(),
                this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
    }
}
