package Model.Buffs;

import Model.enums.BuffType;
import Model.game.Cell;
import Model.enums.TargetCommunity;

public class Poison extends Buff {
    public Poison(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted, TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("Poison");
    }

    @Override
    public void doEffect() {
        getCard().decrementOfHp(getBuffPower());
    }

    @Override
    public void addBuff(Cell cell) {
        setCard(cell.getCard());
        Buff.addBuff(this.copy());
    }

    public Buff copy(){
        return new Poison(BuffType.POISON, this.getRemainTime(), this.getBuffPower(), this.getActivationTime(), this.isStarted(), this.getTargetCommunity());
    }
}
