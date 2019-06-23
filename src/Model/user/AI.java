package Model.user;

import Controller.Controller;
import Model.card.Card;
import Model.game.Cell;
import Model.game.Game;
import Model.item.UsableItem;
import Model.user.User;

import java.util.Map;

import static java.lang.Math.abs;

public class AI {
    private static AI instanse = new AI();

    private AI() {
    }

    public static AI getInstance() {
        return instanse;
    }

    private Game game = Game.getInstance();
    private Cell cell;
    private User ai = new User("AI", "0");
    private int counter;

    public User getAI() {
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


    public void action() {

    }

    public void getCommand() {
        game = Controller.getGame();
        if (cardsCanInsert() && insertTarget() && !inserted) {
            commandInsert();
            inserted = true;
        }
        if (!selected) {
            if (cardsCanSelect()) {
                selected = true;
                commandSelect();
                moved = false;
                attacked = false;
            }
        }
        if (cardCanMove() && moveTarget() && !moved && selected) {
            moved = true;
            commandMove();
        }
        if (cardCanAttack() && attackTarget() && !attacked && selected && !reselected) {
            reselected = true;
            commandSelect();
        }
        if (cardCanAttack() && attackTarget() && !attacked && selected) {
            attacked = true;
            selected = false;
            commandAttack();
        }
        if (allSelected) {
            endTurn();
        } else {
            selected = false;
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
            if (entry.getValue().getMP() <= game.getMana()) {
                insertCard = entry.getValue();
                return true;
            }
        }
        return false;
    }

    public boolean insertTarget() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (game.isCellValidForInsert(i, j)) {
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
}