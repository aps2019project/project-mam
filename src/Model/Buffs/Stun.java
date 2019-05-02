package Model.Buffs;

import Model.BuffType;
import Model.TargetCommunity;

public class Stun extends Buff {
    public Stun(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }
}
