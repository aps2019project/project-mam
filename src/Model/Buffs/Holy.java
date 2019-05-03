package Model.Buffs;

import Model.BuffType;
import Model.TargetCommunity;

public class Holy extends Buff {
    public Holy(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
    }
}
