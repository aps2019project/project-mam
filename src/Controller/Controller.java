package Controller;

import View.View;
import View.Request;
import Model.*;

public class Controller {

    private static final Controller CONTROLLER = new Controller();
    private Controller(){}

    static Controller getInstance(){
        return CONTROLLER;
    }
    private View view = View.getInstance();



//------------------------------user account -----------------------------

    void saveAccount(Request request) {
    }

    void createAccount(Request request) {
    }
    void loginAccount(Request request){
    }
    boolean isAccountValidForLogin(Request request){
        return true;
    }

    void logoutAccount(Request request){}

    void showLeaderBoard(Request request){}
    //--------------------------------------collection------------------------------

    void showCollection(Request request){}

    void searchInCollection(Request request){}

    void saveCommandInCollection(){}

    void createDeck(Request request){}

    void deleteDeck(Request request){}

    void addCardToDeck(Request request){}               //???????

    void removeFromDeck(Request request){}

    void isDeckValid(Request request){}

    void selectMainDeck(Request request){}

    void showDeck(){}

    void showDeck(Request request){}
    //------------------------------------------Shop-----------------------

    void searchInShop(Request request){}

    void showCollectionInShop(Request request){}

    void buyCard(Request request){}

    void sellCard(Request request){}

    void showShop(){}

}
