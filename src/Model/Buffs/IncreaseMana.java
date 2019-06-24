package Model.Buffs;

import Model.enums.BuffType;
import Model.game.Game;
import Model.enums.TargetCommunity;

public class IncreaseMana extends Buff {
    public IncreaseMana(BuffType type, int time, int buffPower, TargetCommunity targetCommunity) {
        super(type, time, buffPower, targetCommunity);
        this.setKind("IncreaseMana");
    }

    private Game game;
    public IncreaseMana(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                        TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
        this.setKind("IncreaseMana");
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public Buff copy() {
        IncreaseMana copy = new IncreaseMana(BuffType.INCREASE_MANA, this.getRemainTime(), this.getBuffPower(),
                this.getActivationTime(), this.isStarted(), this.getTargetCommunity());
        copy.setGame(game);
        return copy;
    }

    @Override
    public void doEffect() {
        if (game.getTurn() % 2 == 1)
            game.setExtraPlayer1Mana(getBuffPower());
        else
            game.setExtraPlayer2Mana(getBuffPower());
    }

    @Override
    public void removeBuff() {
        if (game.getTurn() % 2 == 1)
            game.setExtraPlayer1Mana(0);
        else
            game.setExtraPlayer2Mana(0);
        getBuffs().remove(this);
    }
}
