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
                        controller.showLeaderBoard(request);
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
            }                                                        // main menu while

            while (stateOfMenu.equals("collection Menu")) {
                view.showHelpForMainMenu();
                Request request = new Request();
                request.getNewCommand();
                if (request.getRequestType("collection Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(request.getError());
                    continue;
                }
                switch (request.getRequestType("collection Menu")){
                    case EXIT:
                        stateOfMenu = "main Menu";
                        break;
                    case COLLECTION_SHOW:
                        controller.showCollection(request);
                        break;
                    case COLLECTION_SEARCH:
                        controller.searchInCollection(request);
                        break;
                    case COLLECTION_SAVE:
                        controller.saveCommandInCollection();
                        break;
                    case COLLECTION_CREATE_DECK:
                        controller.createDeck(request);
                        break;
                    case COLLECTION_DELETE_DECK:
                        controller.deleteDeck(request);
                        break;
                    case COLLECTION_ADD:
                        controller.addCardToDeck(request);
                        break;
                    case COLLECTION_REMOVE:
                        controller.removeFromDeck(request);
                        break;
                    case COLLECTION_VALIDATE_DECK:
                        controller.isDeckValid(request);
                        break;
                    case COLLECTION_SELECT_DECK:
                        controller.selectMainDeck(request);
                        break;
                    case COLLECTION_SHOW_ALL_DECKS:
                        controller.showDeck();
                        break;
                    case COLLECTION_SHOW_DECK:
                        controller.showDeck(request);
                    case HELP:
                        view.showHelpForCollectionMenu();
                }
            }                                                     // collection menu while



        }                                                           // project while


    }
}
