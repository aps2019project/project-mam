package view;

import Controller.Controller;
import Model.ErrorType;

public class ShopMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForShopMenu();
    }

    public void handleCommand(String command) {

        if (command.matches("show collection"))
            controller.showCollection();
        else if (command.matches("search .*"))
            controller.searchInShop(command.substring(7));
        else if (command.matches("search collection .*"))
            controller.searchInCollection(command.substring(18));
        else if (command.matches("buy .*"))
            controller.buy(command.substring(4));
        else if (command.matches("sell .*"))
            controller.sell(command.split(" ")[1]);
        else if (command.matches("show"))
            controller.showShop();
        else if (command.matches("help"))
            help();
        else if (command.matches("exit"))
            view.back();
        else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        view.show("----------<Shop>---------");
    }
}
