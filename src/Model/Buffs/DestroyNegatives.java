package Model.Buffs;

import Model.enums.BuffType;
import Model.enums.TargetCommunity;

public class DestroyNegatives extends Buff {
    public DestroyNegatives(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
    }

    public DestroyNegatives(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                            TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("DestroyNegatives");
    }

    @Override
    public Buff copy() {
        return new DestroyNegatives(BuffType.REMOVE_INSIDERS_BUFFS, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(),
                this.isStarted(), this.getTargetCommunity());
    }

    @Override
    public void doEffect() {
        for (Buff buff : Buff.getBuffs()) {
            if (buff.getCard().equals(getCard()) && !buff.isContinous() && buff.getType() != BuffType.ATTACK_POWER &&
                    buff.getType() != BuffType.HOLY && buff.getType() != BuffType.HEALTH_POWER){
                buff.removeBuff();
            }
        }
    }
}
