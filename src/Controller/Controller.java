package Controller;

import Model.*;
import view.*;

import java.lang.String;

public class Controller {

    private static final Controller CONTROLLER = new Controller();

    private Controller() {
        Shop.importCards();
    }

    public static Controller getInstance() {
        return CONTROLLER;
    }

    private View view = View.getInstance();
    private Shop shop = Shop.getInstance();
    //private Collection collection = new Collection();
    private User user = new User();

    public User getFirstUser() {
        return user;
    }

    public User getSecondUser(String userName) {
        if (isUserNameValid(userName)){
            return User.getUser(userName);
        } else view.printError(ErrorType.INVALID_USERNAME);
        return null;
    }

    public boolean isUserNameValid (String userName){
        return !User.isUserNameNew(userName) && !user.getName().equalsIgnoreCase(userName);
    }


    //------------------------------user account -----------------------------

    public void saveAccount(String string) {
    }

    public void createAccount(String command) {
        String userName = command.substring(15);
        view.show("password :");
        String password = view.getPassword();
        if (User.isUserNameNew(userName))
            User.addUser(new User(userName, password));
        else view.printError(ErrorType.DUPLICATE_USERNAME);
    }

    public void loginAccount(String command) {              //????? problem
        String userName = command.split(" ")[1];
        view.show("password :");
        String password = view.getPassword();
        if (!User.isUserNameNew(userName)) {
            if (User.isPassCorrect(userName, password)) {
                user = User.login(userName, password);
                view.getPages().push(new MainMenuPage());
            } else view.printError(ErrorType.INCORRECT_PASSWORD);
        } else view.printError(ErrorType.INVALID_USERNAME);
    }

    public void logoutAccount(String command) {
        user = null;
    }

    public void showLeaderBoard(String command) {
    }

    public void showUsers() {
        StringBuilder info = new StringBuilder();
        info.append("Accounts:\n");
        for (User users : User.getUsers()) {
            if (!users.getName().equalsIgnoreCase(user.getName())){
                info.append("\t").append(users.getName()).append("\n");
            }
        }
        view.show(info.toString());
    }

    //--------------------------------------collection------------------------------

    public void showCollection() {
        view.show(user.getCollection().showCollection());
    }

    public void searchInCollection(String name) {
        if (user.getCollection().searchCardInCollection(name)) {
            view.printError(ErrorType.FOUND_CARD);
            view.show("Card ID: " + user.getCollection().getCardId(name));
        } else if (user.getCollection().searchItemInCollection(name)) {
            view.printError(ErrorType.FOUND_ITEM);
            view.show("Item ID: " + user.getCollection().getItemId(name));
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void saveCollection() {
    }

    public void createDeck(String deckName) {
        if (!user.getCollection().checkIsExistDeck(deckName)) {
            user.getCollection().createDeck(deckName);
        } else view.printError(ErrorType.DUPLICATE_DECK);

    }

    public void deleteDeck(String deckName) {
        if (user.getCollection().checkIsExistDeck(deckName)) {
            user.getCollection().deleteDeck(deckName);
        } else view.printError(ErrorType.NOT_FOUND_DECK);
    }

    public void addCardToDeck(String cardId, String deckName) {
        if (!user.getCollection().checkIsExistDeck(deckName)) {
            view.printError(ErrorType.NOT_FOUND_DECK);
            return;
        }
        int cardID = Integer.parseInt(cardId);
        if (user.getCollection().getCard(cardID) != null) {
            if (!user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (user.getCollection().getDeck(deckName).getCards().size() < 20) {
                    if (!user.getCollection().getDeck(deckName).cardIsExist(cardID)) {
                        user.getCollection().addCardToDeck(user.getCollection().getCard(cardID), deckName);
                    } else view.printError(ErrorType.REPETITIVE_CARD);
                } else view.printError(ErrorType.TWENTY_CARD);
            } else if (user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (user.getCollection().getDeck(deckName).getHero() != null) {
                    user.getCollection().addHeroToDeck(user.getCollection().getCard(cardID), deckName);
                } else view.printError(ErrorType.EXTRA_HERO);
            }
        } else if (user.getCollection().getItem(cardID) != null) {
            if (user.getCollection().getDeck(deckName).getItem() != null) {
                user.getCollection().addItemToDeck(user.getCollection().getItem(cardID), deckName);
            } else view.printError(ErrorType.EXTRA_USABLEITEM);
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM_IN_COLLECTION);
    }

    public void selectMainDeck(String deckName) {

    }

    public boolean isDeckValid(String deckName) {
        return true;
    }

    public void showAllDecks() {
    }

    public void showDeck(String command) {
    }

    //------------------------------------------Shop-----------------------
    public void searchInShop(String name) {
        if (shop.searchCard(name)) {
            view.printError(ErrorType.FOUND_CARD);
            view.show(shop.getCardInfo(name));
        } else if (shop.searchItem(name)) {
            view.printError(ErrorType.FOUND_ITEM);
            view.show(shop.getItemInfo(name));
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void buy(String name) {
        if (shop.cardNameIsAvailable(name)) {
            if (shop.priceIsEnough(shop.getCardPrice(name), user)) {
                shop.buyCard(name, user);
                view.printError(ErrorType.SUCCESSFUL_BUY);
            } else view.printError(ErrorType.MONEY_IS_NOT_ENOUGH);
        } else if (shop.itemNameIsAvailable(name)) {
            if (shop.isPossibleToAddItem(user)) {
                if (shop.priceIsEnough(shop.getItemPrice(name), user)) {
                    shop.buyItem(name, user);
                    view.printError(ErrorType.SUCCESSFUL_BUY);
                } else view.printError(ErrorType.MONEY_IS_NOT_ENOUGH);
            } else view.printError(ErrorType.THREE_ITEM);
        } else view.printError(ErrorType.UNAVAILABLE_CARD_OR_ITEM);
    }

    public void sell(String id) {
        int ID = Integer.parseInt(id);
        if (shop.sellCard(ID, user)) {
            view.printError(ErrorType.SUCCESSFUL_SELL);
        } else if (shop.sellItem(ID, user)) {
            view.printError(ErrorType.SUCCESSFUL_SELL);
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM);
    }

    public void showShop() {
        view.show(shop.show());
    }

    //----------------------------------battle-------------------------------------

    public boolean isMainDeckValid() {
        return true;
    }

    public boolean isMainDeckValid(String userName) {
        return true;
    }

}
