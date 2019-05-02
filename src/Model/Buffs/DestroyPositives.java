package Model.Buffs;

import Model.BuffType;
import Model.Cell;
import Model.TargetCommunity;

public class DestroyPositives extends Buff {
    public DestroyPositives(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
    }

    @Override
    public void doEffect() {
        super.doEffect();
    }

    @Override
    public void doEffect(Cell cell) {
        super.doEffect(cell);
    }
}
