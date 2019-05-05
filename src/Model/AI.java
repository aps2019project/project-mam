package Model;

import java.util.ArrayList;
import java.util.Map;

import static java.lang.Math.abs;

public class AI {


    private Game game;// = Controller.getInstance().getGame();
    private Cell cell;
    private static final User ai = new User("AI", "0");
    private int counter;

    public static User getAI() {
        return ai;
    }

    private boolean selected = false;

    private String command = null;

    private Card selectedCard;

    private Cell moveTarget = null;

    private Card selectTarget = null;

    private Cell attackTarget = null;

    private Card insertCard = null;

    private Cell insertTarget = null;

    public String getCommand() {
        select();
        if (firstCheckCardsCanMove())
            move();
        if (firstCheckCardsCanAttack())
            attack();
        commandInsertCard();
        endTurn();
        return command;
    }
    public  void endTurn(){
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("End turn");
        command = newCommand.toString();
    }

    public boolean cardCanSelect() {
        for (Cell[] cells : game.getMap().getCells()) {
            for (Cell cell : cells) {
                if (cell.getCard() == null) {
                    selectTarget = cell.getCard();
                    return true;
                }
            }
        }
        return false;
    }

    public void select() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("selected ").append(selectTarget.getName());
        command = newCommand.toString();

    }

    public void commandMoveCard(int x, int y) {
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

    public boolean firstCheckCardsCanMove() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().isCanMove()) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (cardCanMove(entry.getValue().getCard())) {
                commandMoveCard(moveTarget.getColumn(), moveTarget.getRow());
                break;
            }
        }
    }

    public boolean cardsCanInsert() {
        for (Map.Entry<Integer, Card> entry : game.getFirstPlayerHand().entrySet()) {
            if (entry.getValue().getCardType().equalsIgnoreCase("minion")) {
                insertCard = entry.getValue();
                if (checkCellInsert(insertCard))
                    return true;
            }
        }
        return false;
    }


    public boolean checkCellInsert(Card card2) {
        for (Cell[] cells : game.getMap().getCells()) {
            for (Cell cell : cells) {
                for (Map.Entry<Integer, Cell> entry2 : game.getMap().getFirstPlayerCellCard().entrySet()) {
                    if (game.getMap().getManhatanDistance(cell, entry2.getValue()) < card2.getTargetCommunity()) {
                        insertTarget = cell;
                        return true;
                    } else {
                        insertTarget = cell;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void commandInsertCard() {
        if (cardsCanInsert()) {
            StringBuilder newCommand = new StringBuilder();
            newCommand.append("insert to ").append(insertCard).append("in").append(insertTarget.getColumn())
                    .append(" ").append(insertCard.getRow());
            command = newCommand.toString();
            counter += 1;
        }
    }

    public boolean cardsCanAttack(Card card) {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().getName().equalsIgnoreCase(card.getName())) {
                for (Cell[] cells : game.getMap().getCells()) {
                    for (Cell cell : cells) {
                        for (Map.Entry<Integer, Cell> entry2 : game.getMap().getFirstPlayerCellCard().entrySet()) {
                            if (game.getMap().getManhatanDistance(cell, entry2.getValue()) < entry.getValue().getCard().getTargetCommunity()) {
                                attackTarget = cell;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean firstCheckCardsCanAttack() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().isCanAttack()) {
                return true;
            }
        }
        return false;
    }

    public void attack() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (cardsCanAttack(entry.getValue().getCard())) {
                commandAttackCard(attackTarget.getColumn(), attackTarget.getRow());
                break;
            }
        }
    }

    public void commandAttackCard(int x, int y) {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("attack to ").append(x).append(" ").append(y);
        command = newCommand.toString();
        counter += 1;
    }

}
