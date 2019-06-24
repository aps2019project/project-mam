package Model.Buffs;

import Model.enums.BuffType;
import Model.game.Cell;
import Model.enums.TargetCommunity;

public class CellEffect extends Buff {
    public CellEffect(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
    }

    public CellEffect(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                      TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("CellEffect");
    }

    @Override
    public void doEffect(Cell cell) {
        super.doEffect(cell);
    }

    @Override
    public Buff copy() {
        return super.copy();    }
}
