package Controller;

import Model.user.AI;
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
    private AI ai = AI.getInstance();
    private static Game game = Game.getInstance();
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
        AI.getInstance().getAI().setMainDeck(Shop.getDecks().get(mission - 1));
        int counter = 1;
        for (Card card : AI.getInstance().getAI().getMainDeck().getCards()) {
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
