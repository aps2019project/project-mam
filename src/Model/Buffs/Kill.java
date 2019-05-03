package Model.Buffs;

import Model.BuffType;
import Model.TargetCommunity;

public class Kill extends Buff {
    public Kill(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }
}
