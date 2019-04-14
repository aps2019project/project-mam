package Controller;

import Model.RequestType;
import view.View;

public class MenuController {

    /*private View view = View.getInstance();
    private Controller controller = Controller.getInstance();

    public void main() {
        String stateOfMenu = "account";
        boolean isProjectEnded = false;
        while (!isProjectEnded) {

            boolean shouldShowHelp = true;
            while (stateOfMenu.equals("account")) {
                if (shouldShowHelp) {
                    view.showHelpForAccountMenu();
                    shouldShowHelp = false;
                }
                String string = new String();
                string.getNewCommand();
                if (string.getRequestType("account") == RequestType.INVALID_COMMAND) {
                    view.printError(string.getError());
                    continue;
                }
                switch (string.getRequestType("account")) {
                    case ACCOUNT_CREATE_ACCOUNT:
                        controller.createAccount(string);
                        break;
                    case ACCOUNT_SAVE:
                        controller.saveAccount(string);
                        break;
                    case ACCOUNT_LOGIN:
                        if (controller.isAccountValidForLogin(string))
                            controller.loginAccount(string);
                        else
                            view.printError(string.getError());
                        break;
                    case ACCOUNT_LOGOUT:
                        controller.logoutAccount(string);
                        break;
                    case ACCOUNT_SHOW_LEADERBOARD:
                        controller.showLeaderBoard(string);
                        break;
                    case HELP:
                        view.showHelpForAccountMenu();
                    case EXIT:
                        stateOfMenu = "exit";
                        isProjectEnded = true;
                        break;
                }

            }                                                       // account while
            shouldShowHelp = true;
            while (stateOfMenu.equals("main Menu")) {
                if (shouldShowHelp) {
                    view.showHelpForMainMenu();
                    shouldShowHelp = false;
                }
                String string = new String();
                string.getNewCommand();
                if (string.getRequestType("main Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(string.getError());
                    continue;
                }
                switch (string.getRequestType("main Menu")) {
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
            shouldShowHelp = true;
            while (stateOfMenu.equals("collection Menu")) {
                if (shouldShowHelp) {
                    view.showHelpForMainMenu();
                    shouldShowHelp = false;
                }
                String string = new String();
                string.getNewCommand();
                if (string.getRequestType("collection Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(string.getError());
                    continue;
                }
                switch (string.getRequestType("collection Menu")) {
                    case EXIT:
                        stateOfMenu = "main Menu";
                        break;
                    case COLLECTION_SHOW:
                        controller.showCollection(string);
                        break;
                    case COLLECTION_SEARCH:
                        controller.searchInCollection(string);
                        break;
                    case COLLECTION_SAVE:
                        controller.saveCommandInCollection();
                        break;
                    case COLLECTION_CREATE_DECK:
                        controller.createDeck(string);
                        break;
                    case COLLECTION_DELETE_DECK:
                        controller.deleteDeck(string);
                        break;
                    case COLLECTION_ADD:
                        controller.addCardToDeck(string);
                        break;
                    case COLLECTION_REMOVE:
                        controller.removeFromDeck(string);
                        break;
                    case COLLECTION_VALIDATE_DECK:
                        controller.isDeckValid(string);
                        break;
                    case COLLECTION_SELECT_DECK:
                        controller.selectMainDeck(string);
                        break;
                    case COLLECTION_SHOW_ALL_DECKS:
                        controller.showDeck();
                        break;
                    case COLLECTION_SHOW_DECK:
                        controller.showDeck(string);
                    case HELP:
                        view.showHelpForCollectionMenu();
                }
            }                                             // collection menu while
            shouldShowHelp = true;
            while (stateOfMenu.equals("shop Menu")) {
                if (shouldShowHelp) {
                    view.showHelpForMainMenu();
                    shouldShowHelp = false;
                }
                String string = new String();
                string.getNewCommand();
                if (string.getRequestType("shop Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(string.getError());
                    continue;
                }
                switch (string.getRequestType("shop Menu")) {
                    case EXIT:
                        stateOfMenu = "main Menu";
                        break;
                    case HELP:
                        view.showHelpForShopMenu();
                        break;
                    case SHOP_SHOW_COLLECTION:
                        controller.showCollectionInShop(string);
                    case SHOP_SEARCH:
                        controller.searchInShop(string);
                    case SHOP_SEARCH_COLLECTION:
                        controller.searchInCollection(string);
                    case SHOP_BUY_CARD:
                        controller.buyCard(string);
                    case SHOP_SELL_CARD:
                        controller.sellCard(string);
                    case SHOP_SHOW:
                        controller.showShop();
                }
            }                                                       // shop menu while

            shouldShowHelp = true;
            while (stateOfMenu.equals("battle Menu")) {
                controller.isMainDeckValid();



                if (shouldShowHelp) {
                    view.showHelpForBattleMenu();
                    shouldShowHelp = false;
                }

                String string = new String();
                string.getNewCommand();
                if (string.getRequestType("battle Menu") == RequestType.INVALID_COMMAND) {
                    view.printError(string.getError());
                    continue;
                }


            }
        }                                                           // project while


    }*/
}
