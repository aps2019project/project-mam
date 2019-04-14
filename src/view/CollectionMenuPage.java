package view;

import Controller.Controller;

public class CollectionMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForCollectionMenu();
    }


    @Override
    public void handleCommand(String command) {
        if (command.matches("show"))
            controller.showCollection(command);
    }
}
