package Model.Buffs;

import Model.BuffType;
import Model.Game;
import Model.TargetCommunity;

public class IncreaseMana extends Buff {
    private Game game;
    public IncreaseMana(BuffType type, int remainTime, int buffPower, int activationTime, boolean isStarted,
                        TargetCommunity targetCommunity) {
        super(type, remainTime, buffPower, activationTime, isStarted, targetCommunity);
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
            game.setFirstPlayerMana(getBuffPower() + game.getBasicMana());
        else
            game.setSecondPlayerMana(getBuffPower() + game.getBasicMana());
    }
}
