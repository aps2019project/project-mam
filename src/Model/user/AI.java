package Model.user;

import Controller.GameController.MapController;
import Model.card.Card;
import Model.game.Cell;
import Model.game.Game;
import command.clientCommand.*;
import gson.GsonWriter;
import javafx.application.Platform;
import view.BattleMenu.MainBattleMenuPage;
import view.pages.Page;

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
            endTurn();
            /*if (allSelected) {
                endTurn();
            } else {
                selected = false;
            }*/
        }).start();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
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
        GsonWriter.sendClientCommand(new EndTurnCmd(), Page.getOutput());
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
                if (game.isCellValidForInsertMinion(i, j)) {
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
        GsonWriter.sendClientCommand(new InsertCmd(insertCard.getName(),insertTarget.getRow(), insertCard.getColumn()), Page.getOutput());
        sleep(2000);
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
        GsonWriter.sendClientCommand(new SelectCmd(String.valueOf(selectedCard.getId())), Page.getOutput());
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
        GsonWriter.sendClientCommand(new MoveCmd(moveTarget.getRow(), moveTarget.getColumn()), Page.getOutput());
        sleep(1000);
    }

    private boolean cardCanAttack() {
        return selectedCard.isCanAttack();
    }

    private boolean attackTarget() {
        for (Map.Entry<Integer, Cell> entry : game.getMap().getFirstPlayerCellCard().entrySet()) {
            if (game.isOppAvailableForAttack(entry.getValue().getCard().getId(), selectedCard.getId(), game.getTurn())) {
                attackTarget = entry.getValue().getCard();
                return true;
            }
        }
        return false;
    }

    private void commandAttack() {
        Platform.runLater(() -> mapCtrl.attack(attackTarget.getId()));
        GsonWriter.sendClientCommand(new AttackCmd(String.valueOf(attackTarget.getId())), Page.getOutput());
        sleep(2500);
    }
}