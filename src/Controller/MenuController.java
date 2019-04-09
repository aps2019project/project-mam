package Controller;

import Model.RequestType;
import View.Request;
import View.View;

public class MenuController {

    private View view = View.getInstance();
    private Controller controller = Controller.getInstance();

    public void main() {
        String stateOfMenu = "account";
        boolean isProjectEnded = false;
        while (!isProjectEnded) {


            while (stateOfMenu.equals("account")) {
                view.showHelpForAccountMenu();
                Request request = new Request();
                request.getNewCommand();
                if (request.getRequestType("account") == RequestType.INVALID_COMMAND) {
                    view.printError(request.getError());
                    continue;
                }
                switch (request.getRequestType("account")) {
                    case ACCOUNT_CREATE_ACCOUNT:
                        controller.createAccount(request);
                        break;
                    case ACCOUNT_SAVE:
                        controller.saveAccount(request);
                        break;
                    case ACCOUNT_LOGIN:
                        if (controller.isAccountValidForLogin(request))
                            controller.loginAccount(request);
                        else
                            view.printError(request.getError());
                        break;
                    case ACCOUNT_LOGOUT:
                        controller.logoutAccount(request);
                        break;
                    case ACCOUNT_SHOWlEADERBOARD:
                        controller.showLeaderBoard(request, view);
                        break;
                    case HELP:
                        view.showHelpForAccountMenu();
                    case EXIT:
                        stateOfMenu = "exit";
                        isProjectEnded = true;
                        break;
                }

            }                                                       // account while

            while (stateOfMenu.equals("main Menu")){
                view.showHelpForMainMenu();
                Request request = new Request();
                request.getNewCommand();
                if (request.getRequestType("main Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(request.getError());
                    continue;
                }
                switch (request.getRequestType("main Menu")){
                    case MAIN_COLLECTION:
                        stateOfMenu = "collection Menu";
                        break;
                    case MAIN_BATTLE:
                        stateOfMenu = "battle Menu";
                        break;
                    case MAIN_SHOP:
                        stateOfMenu = "shop Menu";
                        break;
                    case HELP:
                        view.showHelpForMainMenu();
                        break;
                    case EXIT:
                        stateOfMenu = "exit";
                        isProjectEnded = true;
                        break;
                }
            }

        }                                                           // project while


    }
}
