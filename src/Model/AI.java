package Model;

import Controller.Controller;

import java.util.ArrayList;
import java.util.Map;
import static java.lang.Math.abs;

public class AI {


    private Game game;
    private static final User ai = new User("AI", "0");

    public static User getAI() {
        return ai;
    }

    private String command = null;

    public void setGame(Game game) {
        this.game = game;
    }

    private ArrayList<Card> selectedCard;

    private Cell moveTarget = null;

    public String getCommand() {
        return command;
    }

    public void moveCard(int x, int y){
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("move to ").append(x).append(" ").append(y);
        command = newCommand.toString();
    }

    public boolean cardCanMove(Card card){
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().getName().equalsIgnoreCase(card.getName())){
                for (Cell[] cells : game.getMap().getCells()) {
                    for (Cell cell : cells) {
                        if (game.getMap().getManhatanDistance(cell, entry.getValue()) < entry.getValue().getCard().getTargetCommunity()){
                            moveTarget = cell;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void moveCards(){
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (cardCanMove(entry.getValue().getCard())) {
                moveCard(moveTarget.getColumn(), moveTarget.getRow());
            }
        }
    }

    public void selectCard(){

    }

}
