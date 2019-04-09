package Controller;

import View.View;
import View.Request;
import Model.*;

public class Controller {

    private View view = View.getInstance();


    public void main() {
        String stateOfMenu = "account";
        boolean isProjectEnded = false;
        while (!isProjectEnded) {
            account:
            while (stateOfMenu.equals("account")) {
                Request request = new Request();
                request.getNewCommand();
                if (request.getRequestType("menu") == RequestType.INVALID_COMMAND) {
                    view.printError(request.getError());
                    continue;
                }
                switch (request.getRequestType("menu")) {
                    case ACCOUNT_CREATE_ACCOUNT:
                        createAccount(request);
                        break;
                    case ACCOUNT_SAVE:
                        saveAccount(request);
                        break;
                    case ACCOUNT_LOGIN:


                }
            }
        }

    }

    private void saveAccount(Request request) {
    }

    private void createAccount(Request request) {
    }
    private void loginAccount(Request request){}

    private void logoutAccount(Request request){}

    private void showLeaderBoard(Request request){}

    private void helpForAccount(Request request){}

}
