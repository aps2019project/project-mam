package Controller;

import Model.AI;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.deck.Deck;
import Model.enums.ErrorType;
import Model.enums.SPActivationTime;
import Model.game.Game;
import Model.shop.Shop;
import Model.user.User;
import view.View;

import java.util.ArrayList;

public class Controller {

    private static final Controller CONTROLLER = new Controller();

    private Controller() {
       // Shop.importCards();
    }

    public static Controller getInstance() {
        return CONTROLLER;
    }


    private Shop shop = Shop.getInstance();
    private User user = new User();
    private AI ai = new AI();
    private static Game game = new Game();
    private View view = View.getInstance();
    private Deck secondDeck = new Deck();

    public void setGame(Game game) {
        this.game = game;
    }

    public static Game getGame() {
        return game;
    }

    public Deck getSecondDeck() {
        return secondDeck;
    }

    public void setSecondDeck(Deck secondDeck) {
        this.secondDeck = secondDeck;
    }

    public User getFirstUser() {
        return user;
    }

    public User getSecondUser(String userName) {
        if (isUserNameValid(userName)) {
            return User.getUser(userName);
        } else view.printError(ErrorType.INVALID_USERNAME);
        return null;
    }

    public boolean isUserNameValid(String userName) {
        return !User.isUserNameNew(userName) && !user.getName().equalsIgnoreCase(userName);
    }


    //------------------------------user account -----------------------------

    public void saveAccount() {
    }





    public void logoutAccount() {
        user = null;
    }

    public void showLeaderBoard() {
        view.show("*LeaderBoard* :");
        view.show(User.showUsers());
    }



    //------------------------------------AI------------------------------------

    public String getAiCommand(){
        return ai.getCommand();
    }

    public boolean isAiTurn(){
        return game.getTurn() % 2 == 0;
    }

    public void setAiMainDeck(int mission){
        AI.getAI().setMainDeck(Shop.getDecks().get(mission - 1));
        int counter = 1;
        for (Card card : AI.getAI().getMainDeck().getCards()) {
            card.setId(counter);
            counter++;
        }

    }

    //--------------------------------------collection------------------------------

    public void showCollection() {
        view.show(user.getCollection().showCollection());
    }





    public void showAllDecks() {
        view.show(user.getCollection().showAllDecks());
    }



    //------------------------------------------Shop-----------------------


    public void showShop() {
        view.show(shop.show());
    }

    //----------------------------------battle-------------------------------------



    public void showGameInfo() {
        view.show(game.getGameInfo());
    }

    public void showMyMinions() {
        view.show(game.showMyMinions());
    }

    public void showOpMinions() {
        view.show(game.showOpMinions());
    }

    public void showCardInfo(String cardId) {
        if (user.getMainDeck().cardIsExist(Integer.parseInt(cardId))) {
            view.show(game.showCardInfo(Integer.parseInt(cardId)));
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void showHand() {
        view.show(game.showHand());
    }

    public void selectCard(String cardId) {
        if (game.isCardInPlayerCellCard(Integer.parseInt(cardId))) {
            game.selectCard(Integer.parseInt(cardId));
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void moveCard(String x, String y) {
        if (game.getCurrentCard().isCanMove()) {
            if (game.cardCanMove(Integer.parseInt(x), Integer.parseInt(y))) {
                game.moveCurrentCardTo(Integer.parseInt(x), Integer.parseInt(y));
                StringBuilder message = new StringBuilder();
                message.append(game.getCurrentCard().getId()).append(" moved to ");
                message.append(x).append(" ").append(y);
                ErrorType.SUCCESSFUL_MOVING_CARD.setMessage(message.toString());
                view.printError(ErrorType.SUCCESSFUL_MOVING_CARD);
            } else view.printError(ErrorType.INVALID_TARGET);
        } else view.printError(ErrorType.CARD_CAN_NOT_MOVE);
    }

    public void insertCard(String cardName, String x, String y) {
        if (game.isCardInPlayerHand(cardName)) {
            if (game.haveEnoughMana(cardName)) {
                if (game.isCellValidForInsert(Integer.parseInt(x), Integer.parseInt(y))) {
                    game.insertPlayerCard(cardName, Integer.parseInt(x), Integer.parseInt(y));
                    StringBuilder message = new StringBuilder();
                    message.append(cardName).append(" with ").append(game.getCurrentCard().getId());
                    message.append(" inserted to ( ").append(x).append(", ").append(y).append(" )");
                    ErrorType.SUCCESSFUL_INSERTING_CARD.setMessage(message.toString());
                    view.printError(ErrorType.SUCCESSFUL_INSERTING_CARD);
                } else view.printError(ErrorType.INVALID_TARGET);
            } else view.printError(ErrorType.MANA_IS_NOT_ENOUGH_INSERT);
        } else view.printError(ErrorType.INVALID_CARD_NAME);
    }

    public void endTurn() {
        view.show("---<End turn>---");
        game.endTurn();
    }

    public void showNextCard() {
        view.show("Next Card:");
        view.show(game.showNextCard());
    }

    public void attack(String oppCardId) {
        if (game.getCurrentCard().isCanAttack()) {
            if (game.isCardInOppPlayerCellCard(Integer.parseInt(oppCardId))) {
                if (game.isOppAvailableForAttack(Integer.parseInt(oppCardId), game.getCurrentCard().getId())) {
                    game.attack(Integer.parseInt(oppCardId));
                    view.printError(ErrorType.SUCCESSFUL_ATTACK);
                } else view.printError(ErrorType.UNAVAILABLE_OPP_ATTACK);
            } else view.printError(ErrorType.INVALID_CARD_ID);
        } else {
            StringBuilder message = new StringBuilder();
            message.append("card with ").append(game.getCurrentCard().getId()).append(" can't attack");
            ErrorType.CARD_CAN_NOT_ATTACK.setMessage(message.toString());
            view.printError(ErrorType.CARD_CAN_NOT_ATTACK);
        }
    }

    public void comboAttack(String oppCardId, ArrayList<String> myCardsId) {
        int[] attackersId = new int[myCardsId.size()];
        int counter = 0;
        for (String cardId : myCardsId) {
            attackersId[counter] = Integer.parseInt(cardId);
            counter++;
        }
        if (game.isCardInOppPlayerCellCard(Integer.parseInt(oppCardId))) {
            game.comboAttack(Integer.parseInt(oppCardId), attackersId);
        } else view.printError(ErrorType.INVALID_CARD_ID);
    }

    public void useSP(String x, String y) {
        if (game.getCurrentCard() instanceof Minion && game.getCurrentCard().getSPActivationTime() == SPActivationTime.ON_SPAWN) {
            if (game.getCurrentCard() instanceof Hero && game.getCurrentCard().getCooldown() == 0) {
                game.useSP(Integer.parseInt(x), Integer.parseInt(y));
            } else view.printError(ErrorType.MANA_IS_NOT_ENOUGH_USE_SP);
        } else view.printError(ErrorType.CARD_HAVE_NOT_SP);
    }

    public boolean isEnded() {
        return game.isGameEnd();
    }

    public void showEnd() {
        view.show("Ended\nWinner: ");
        view.show(game.getWinnerName());
        view.show("Price: ");
        view.show(String.valueOf(game.getPrice()));
    }

    public void showInfoInGraveyard(String cardId) {
        view.show(game.getInfoInGraveYard(Integer.parseInt(cardId)));
    }

    public void showCardsInGraveyard() {
        view.show(game.showInGraveYard());
    }

    public void selectCollectible(String itemId) {
        game.selectCollectableItem(Integer.parseInt(itemId));
    }

    public void showCollectibles() {
        view.show(game.showCollectables());
    }

    public void showCollectibleInfo() {
        view.show(game.getItemInfo());
    }

    public void useCollectible(){
        game.useCollectible();
    }


}
