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
    User user = new User();


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
    //--------------------------------------collection------------------------------

    public boolean isAccountValidForLogin(String command) {
        return true;
    }

    public void showCollection(String command) {
    }

    public void searchInCollection(String command) {
    }

    public void saveCommandInCollection() {
    }

    public void createDeck(String command) {
    }

    public void deleteDeck(String command) {
    }

    void addCardToDeck(String command) {
    }               //???????

    void removeFromDeck(String command) {
    }

    void isDeckValid(String command) {
    }

    void selectMainDeck(String command) {
    }

    void showDeck() {
    }

    void showDeck(String command) {
    }
    //------------------------------------------Shop-----------------------

    void searchInShop(String command) {
    }

    void showCollectionInShop(String command) {
    }

    void buyCard(String command) {
    }

    void sellCard(String command) {
    }

    void showShop() {
    }

    //----------------------------------battle-------------------------------------

    public void isMainDeckValid(){

    }

}
