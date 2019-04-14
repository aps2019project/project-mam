package view;

import Controller.Controller;

public class ShopMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForShopMenu();
    }

    public void handleCommand(recuest command) {

    }
}
