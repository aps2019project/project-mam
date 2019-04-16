package Controller;

import Model.*;
import view.*;
import java.lang.String;

public class Controller {

    private static final Controller CONTROLLER = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return CONTROLLER;
    }

    private View view = View.getInstance();
    private Shop shop = Shop.getInstance();
    private User user = new User();


//------------------------------user account -----------------------------

    public void saveAccount(String string) {
    }

    public void createAccount(String command) {
        String userName = command.split(" ")[2];
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
        if (!User.isUserNameNew(userName)){
            if (User.isPassCorrect(userName, password)){
                user = User.login(userName, password);
                view.getPages().push(new MainMenuPage());
            }
            else view.printError(ErrorType.INCORRECT_PASSWORD);
        }
        else view.printError(ErrorType.INVALID_USERNAME);
    }

    public void logoutAccount(String command) {
        user = null;
    }

    public void showLeaderBoard(String command) {
    }

    public void showUsers(){

    }

    //--------------------------------------collection------------------------------

    public void showCollection() {
    }

    public void searchInCollection(String name) {
    }

    public void saveCollection() {
    }

    public void createDeck(String deckName) {
    }

    public void deleteDeck(String deckName) {
    }

    public void addCardToDeck(String deckName) {
    }               //?????

    public void selectMainDeck(String command) {
    }

    public void isDeckValid(String deckName) {
    }

    public void showAllDecks() {
    }

    public void showDeck(String command) {
    }
    //------------------------------------------Shop-----------------------

    public void searchInShop(String name) {
    }

    public void showCollectionInShop(String command) {
    }

    public void buy(String name) {
        if (shop.cardNameIsAvailable(name)){
            if (shop.priceIsEnough(shop.getCardPrice(name), user)) {
                shop.buyCard(name, user);
                view.printError(ErrorType.SUCCESSFUL_BUY);
            }
            else view.printError(ErrorType.MONEY_IS_NOT_ENOUGH);
        }
        else if (shop.itemNameIsAvailable(name)){
            if (shop.isPossibleToAddItem(user)) {
                if (shop.priceIsEnough(shop.getItemPrice(name), user)) {
                    shop.buyItem(name, user);
                    view.printError(ErrorType.SUCCESSFUL_BUY);
                }
                else view.printError(ErrorType.MONEY_IS_NOT_ENOUGH);
            }
            else view.printError(ErrorType.THREE_ITEM);
        }
        else view.printError(ErrorType.UNAVAILABLE_CARD_OR_ITEM);
    }

    public void sell(String name) {
    }

    public void showShop() {
    }

    //----------------------------------battle-------------------------------------

    public boolean isMainDeckValid(){
return true;
    }
    public boolean isMainDeckValid(String userName){
        return true;
    }

}
