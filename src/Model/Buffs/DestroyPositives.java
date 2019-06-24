package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class DestroyPositives extends Buff {
    public DestroyPositives(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                            TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("DestroyPositives");
    }

    @Override
    public void doEffect() {
        for (Buff buff : Buff.getBuffs()) {
            if (buff.getCard().equals(getCard()) && !buff.isContinous() && buff.getType() == BuffType.ATTACK_POWER &&
            buff.getType() == BuffType.HOLY && buff.getType() == BuffType.HEALTH_POWER)
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
