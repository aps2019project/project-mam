package Controller;

import Model.*;
import view.*;

import java.lang.String;
import java.util.ArrayList;

public class Controller {

    private static final Controller CONTROLLER = new Controller();

    private Controller() {
        Shop.importCards();
    }

    public static Controller getInstance() {
        return CONTROLLER;
    }


    private Shop shop = Shop.getInstance();
    // private Collection collection = new Collection();
    private User user = new User();
    private AI ai = new AI();
    private static Game game = new Game();
    private View view = View.getInstance();

    public void setGame(Game game) {
        this.game = game;
    }

    public static Game getGame() {
        return game;
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

    public void createAccount(String command) {
        view = View.getInstance();
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

    public void logoutAccount() {
        user = null;
    }

    public void showLeaderBoard() {
        view.show("*LeaderBoard* :");
        view.show(User.showUsers());
    }

    public void showUsers() {
        StringBuilder info = new StringBuilder();
        info.append("Accounts:\n");
        for (User users : User.getUsers()) {
            if (!users.getName().equalsIgnoreCase(user.getName())) {
                info.append("\t").append(users.getName()).append("\n");
            }
        }
        view.show(info.toString());
    }

    //------------------------------------AI------------------------------------

    public String getAiCommand(){
        return ai.getCommand();
    }

    public boolean isAiTurn(){
        return game.getTurn() % 2 == 0;
    }

    public void setAiMainDeck(int mission){
        AI.getAI().getCollection().setMainDeck(Shop.getDecks().get(mission));
        int counter = 1;
        for (Card card : AI.getAI().getCollection().getMainDeck().getCards()) {
            card.setId(counter);
            counter++;
        }

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
                        view.printError(ErrorType.SUCCESSFUL_ADDING_CARD);
                    } else view.printError(ErrorType.REPETITIVE_CARD);
                } else view.printError(ErrorType.TWENTY_CARD);
            } else if (user.getCollection().getCard(cardID).getCardType().equalsIgnoreCase("hero")) {
                if (user.getCollection().getDeck(deckName).getHero() == null) {
                    user.getCollection().addHeroToDeck(user.getCollection().getCard(cardID), deckName);
                    view.printError(ErrorType.SUCCESSFUL_ADDING_HERO);
                } else view.printError(ErrorType.EXTRA_HERO);
            }
        } else if (user.getCollection().getItem(cardID) != null) {
            if (user.getCollection().getDeck(deckName).getItem() == null) {
                user.getCollection().addItemToDeck(user.getCollection().getItem(cardID), deckName);
                view.printError(ErrorType.SUCCESSFUL_ADDING_ITEM);
            } else view.printError(ErrorType.EXTRA_USABLEITEM);
        } else view.printError(ErrorType.NOT_FOUND_CARD_OR_ITEM_IN_COLLECTION);
    }

    public void selectMainDeck(String deckName) {
        if (user.getCollection().checkIsExistDeck(deckName)) {
            user.getCollection().setMainDeck(deckName);
            view.printError(ErrorType.SELECT_MAIN_DECK);
        } else view.printError(ErrorType.NOT_FOUND_DECK);
    }

    public void validateDeck(String deckName) {
        if (user.getCollection().isValidDeck(deckName)) {
            view.printError(ErrorType.VALID_DECK);
        } else view.printError(ErrorType.INVALID_DECK);
    }

    public void showAllDecks() {
        view.show(user.getCollection().showAllDecks());
    }

    public void showDeck(String deckName) {
        if (user.getCollection().checkIsExistDeck(deckName)) {
            view.show(user.getCollection().showDeck(deckName));
        } else view.printError(ErrorType.NOT_FOUND_DECK);
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
        if (user.getMainDeck() != null) {
            if (user.getCollection().isValidMainDeck()) {
                return true;
            } else {
                view.printError(ErrorType.INVALID_DECK);
                return false;
            }
        }
        view.printError(ErrorType.NOT_SELECT_MAIN_DECK);
        return false;
    }

    public boolean isMainDeckValid(String userName) {
        return User.getUser(userName).getCollection().isValidMainDeck();
    }

    public boolean isDeckValid(String deckName) {
        if (user.getCollection().isValidDeck(deckName)) {
            return true;
        }
        return false;
    }

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
                if (game.isValidCellForInsert(Integer.parseInt(x), Integer.parseInt(y))) {
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
        view.show("ended");
        view.show(String.valueOf(game.getWinner()));
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
