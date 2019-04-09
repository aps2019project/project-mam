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

    void showLeaderBoard(Request request, View view){}

    void helpForAccount(Request request, View view){}

}
