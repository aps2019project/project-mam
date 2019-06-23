package Model.user;

import Controller.GameController.GameController;
import Controller.GameController.MapController;
import Model.card.Card;
import Model.game.Cell;
import Model.game.Game;
import javafx.application.Platform;
import view.BattleMenu.MainBattleMenuPage;

import java.util.Map;

public class AI {
    private static AI instanse = new AI();

    private AI() {
    }

    public static AI getInstance() {
        return instanse;
    }

    private Game game = Game.getInstance();
    private User ai = new User("AI", "0");
    private MapController mapCtrl = MapController.getInstance();

    public User getAI() {
        return ai;
    }

    private boolean allSelected = false;
    private boolean inserted = false;
    private boolean selected = false;
    private boolean reselected = false;
    private boolean moved = false;
    private boolean attacked = false;
    private Card selectedCard = null;
    private Cell moveTarget = null;
    private Card attackTarget = null;
    private Card insertCard = null;
    private Cell insertTarget = null;
    

    public void getCommand() {
        game = Game.getInstance();
        new Thread(() -> {
            if (cardsCanInsert() && insertTarget() && !inserted)
                commandInsert();

            if (!selected && cardsCanSelect()) {
                selected = true;
                moved = false;
                attacked = false;
                commandSelect();
            }
            if (cardCanMove() && moveTarget() && !moved && selected)
                commandMove();

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
        }).start();
    }

    private void sleep(int nanoSecond) {
        try {
            Thread.sleep(nanoSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endTurn() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getSecondPlayerCellCard().entrySet()) {
            entry.getValue().getCard().setSelected(false);
        }
        allSelected = false;
        inserted = false;
        selected = false;
        reselected = false;
        moved = false;
        attacked = false;
        Platform.runLater(() -> MainBattleMenuPage.getController().endTurn());
    }

    private boolean cardsCanInsert() {
        for (Map.Entry<Integer, Card> entry : game.getSecondPlayerHand().entrySet()) {
            if (entry.getValue().getMP() <= game.getMana()) {
                insertCard = entry.getValue();
                return true;
            }
        }
        return false;
    }

    private boolean insertTarget() {
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

    private void commandInsert() {
        inserted = true;
        Platform.runLater(() -> mapCtrl.insertCard(insertCard.getName(),insertTarget.getRow(), insertCard.getColumn()));
        sleep(1000);
    }

    private boolean cardsCanSelect() {
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

    private void commandSelect() {
        Platform.runLater(() -> mapCtrl.selectCard(String.valueOf(selectedCard.getId())));
        sleep(50);
    }

    private boolean cardCanMove() {
        return selectedCard.isCanMove();
    }

    private boolean moveTarget() {
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

    private void commandMove() {
        moved = true;
        Platform.runLater(() -> mapCtrl.moveCard(moveTarget.getRow(), moveTarget.getColumn()));
        sleep(400);
    }

    private boolean cardCanAttack() {
        return selectedCard.isCanAttack();
    }

    private boolean attackTarget() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            if (game.isOppAvailableForAttack(entry.getValue().getCard().getId(), selectedCard.getId())) {
                attackTarget = entry.getValue().getCard();
                return true;
            }
        }
        return false;
    }

    private void commandAttack() {
        Platform.runLater(() -> mapCtrl.attack(attackTarget.getId()));
        sleep(2000);
    }
}