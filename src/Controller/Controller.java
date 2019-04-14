package Controller;

import View.View;
import View.Request;
import Model.*;

public class Controller {

    private static final Controller CONTROLLER = new Controller();

    private Controller() {
    }

    static Controller getInstance() {
        return CONTROLLER;
    }

    private View view = View.getInstance();
    User user = new User();


//------------------------------user account -----------------------------

    void saveAccount(Request request) {
    }

    void createAccount(Request request) {
        String userName = request.getCommand();
        view.show("password :");
        request.getNewCommand();
        String password = request.getCommand();
        if (User.isUserNameNew(userName))
            User.addUser(new User(userName, password));
        else view.printError(ErrorType.DUPLICATE_USERNAME);
    }

    void loginAccount(Request request) {
        String userName = request.getCommand();
        view.show("password :");
        request.getNewCommand();
        String password = request.getCommand();
        User temp = User.login(userName, password);
        if (temp != null)
            user = temp;
    }

    void logoutAccount(Request request) {
        user = null;
    }

    void showLeaderBoard(Request request) {
    }
    //--------------------------------------collection------------------------------

    boolean isAccountValidForLogin(Request request) {
        return true;
    }

    void showCollection(Request request) {
    }

    void searchInCollection(Request request) {
    }

    void saveCommandInCollection() {
    }

    void createDeck(Request request) {
    }

    void deleteDeck(Request request) {
    }

    void addCardToDeck(Request request) {
    }               //???????

    void removeFromDeck(Request request) {
    }

    void isDeckValid(Request request) {
    }

    void selectMainDeck(Request request) {
    }

    void showDeck() {
    }

    void showDeck(Request request) {
    }
    //------------------------------------------Shop-----------------------

    void searchInShop(Request request) {
    }

    void showCollectionInShop(Request request) {
    }

    void buyCard(Request request) {
    }

    void sellCard(Request request) {
    }

    void showShop() {
    }

}
