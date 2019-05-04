package Model;

import Controller.Controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.abs;

public class AI {


    private Game game;// = Controller.getInstance().getGame();
    private static final User ai = new User("AI", "0");
    private int counter;

    public static User getAI() {
        return ai;
    }

    private String command = null;

    private ArrayList<Card> selectedCard;

    private Cell moveTarget = null;

    public String getCommand() {
        if (cardsCanMove())
            moveCards();

        return command;
    }

    public void moveCard(int x, int y) {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("move to ").append(x).append(" ").append(y);
        command = newCommand.toString();
        counter += 1;
    }

    public boolean cardCanMove(Card card) {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().getName().equalsIgnoreCase(card.getName())) {
                for (Cell[] cells : game.getMap().getCells()) {
                    for (Cell cell : cells) {
                        if (game.getMap().getManhatanDistance(cell, entry.getValue()) < entry.getValue().getCard().getTargetCommunity()) {
                            moveTarget = cell;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean cardsCanMove() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().isCanMove()) {
                return true;
            }
        }
        return false;
    }

    public void moveCards() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (cardCanMove(entry.getValue().getCard())) {
                moveCard(moveTarget.getColumn(), moveTarget.getRow());
                break;
            }
        }
    }

    public void selectCard() {

    }

}
