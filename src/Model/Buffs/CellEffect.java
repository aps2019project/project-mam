package Model.Buffs;

import Model.BuffType;
import Model.Cell;
import Model.TargetCommunity;

public class CellEffect extends Buff {
    public CellEffect(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
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
