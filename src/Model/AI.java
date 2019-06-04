package Model;

import Controller.Controller;
import Model.card.Card;
import Model.user.User;

import java.util.Map;

import static java.lang.Math.abs;

public class AI {


    private Controller controller = Controller.getInstance();
    private Game game = Controller.getGame(); //= controller.getGame();
    private Cell cell;
    private static final User ai = new User("AI", "0");
    private int counter;

    public static User getAI() {
        return ai;
    }

    private boolean allSelected = false;
    private boolean inserted = false;
    private boolean selected = false;
    private boolean reselected = false;
    private boolean moved = false;
    private boolean attacked = false;
    private String command = null;
    private Card selectedCard = null;
    private Cell moveTarget = null;
    private Card attackTarget = null;
    private Card insertCard = null;
    private Cell insertTarget = null;

    public String getCommand() {
        game = Controller.getGame();
        if (cardsCanInsert() && insertTarget() && !inserted) {
            commandInsert();
            inserted = true;
            return command;
        }
        if (!selected) {
            if (cardsCanSelect()) {
                selected = true;
                commandSelect();
                moved = false;
                attacked = false;
                return command;
            }
        }
        if (cardCanMove() && moveTarget() && !moved && selected) {
            moved = true;
            commandMove();
            return command;
        }
        if (cardCanAttack() && attackTarget() && !attacked && selected && !reselected) {
            reselected = true;
            commandSelect();
            return command;
        }
        if (cardCanAttack() && attackTarget() && !attacked && selected) {
            attacked = true;
            selected = false;
            commandAttack();
            return command;
        }
        if (allSelected) {
            endTurn();
            return command;
        }else {
            selected = false;
            return "";
        }
    }

    public void endTurn() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            entry.getValue().getCard().setSelected(false);
        }
        allSelected = false;
        inserted = false;
        selected = false;
        reselected = false;
        moved = false;
        attacked = false;
        command = "end turn";
    }

    public boolean cardsCanInsert() {
        for (Map.Entry<Integer, Card> entry : game.getSecondPlayerHand().entrySet()) {
            if (entry.getValue().getMP() <= game.getSecondPlayerMana()) {
                insertCard = entry.getValue();
                return true;
            }
        }
        return false;
    }

    public boolean insertTarget() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (game.isValidCellForInsert(i, j)) {
                    insertTarget = game.getMap().getCells()[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    public void commandInsert() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("insert ").append(insertCard.getName()).append(" in ").append(insertTarget.getRow())
                .append(" ").append(insertTarget.getColumn());
        command = newCommand.toString();
    }

    public boolean cardsCanSelect() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (!entry.getValue().getCard().isSelected()) {
                entry.getValue().getCard().setSelected(true);
                selectedCard = entry.getValue().getCard();
                return true;
            }
        }
        allSelected = true;
        return false;
    }

    public void commandSelect() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("select ").append(selectedCard.getId());
        command = newCommand.toString();
    }

    public boolean cardCanMove() {
        return selectedCard.isCanMove();
    }

    public boolean moveTarget() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (game.cardCanMove(i, j)) {
                    moveTarget = game.getMap().getCells()[i][j];
                    return true;
                }
            }
        }
        return false;
    }

    public void commandMove() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("move to ").append(moveTarget.getRow()).append(" ").append(moveTarget.getColumn());
        command = newCommand.toString();
    }

    public boolean cardCanAttack() {
        return selectedCard.isCanAttack();
    }

    public boolean attackTarget() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            if (game.isOppAvailableForAttack(entry.getValue().getCard().getId(), selectedCard.getId())) {
                attackTarget = entry.getValue().getCard();
                return true;
            }
        }
        return false;
    }

    public void commandAttack() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("attack ").append(attackTarget.getId());
        command = newCommand.toString();
    }

    /*
    public boolean firstCheckCardsCanSelect(){

    }

    public boolean cardCanSelect() {
        for (Cell[] cells : game.getMap().getCells()) {
            for (Cell cell : cells) {
                if (cell.getCard() == null) {
                    selectTarget = cell;
                    return true;
                }
            }
        }
        return false;
    }

    public void select() {
        StringBuilder newCommand = new StringBuilder();
        newCommand.append("selected ").append(selectTarget.getColumn());
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

    public boolean firstCheckCardsCanInsert(){
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            if (entry.getValue().getCard().getMP() <= game.getSecondPlayerMana()){
                return true;
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
    }*/

}
