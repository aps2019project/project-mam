package Model.Buffs;

import Model.BuffType;
import Model.TargetCommunity;

public class DestroyNegatives extends Buff {
    public DestroyNegatives(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }
}
