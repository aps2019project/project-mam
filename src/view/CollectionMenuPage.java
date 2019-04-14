package view;

import Controller.Controller;
import Model.ErrorType;

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
            controller.showCollection();
        else if (command.matches("search .*"))
            controller.searchInCollection(command.split(" ")[1]);
        else if (command.matches("save"))
            controller.saveCollection();
        else if (command.matches("create deck .*"))
            controller.createDeck(command.split(" ")[2]);
        else if (command.matches("delete deck .*"))
            controller.deleteDeck(command.split(" ")[2]);
        else if (command.matches("add \\d+ to deck .*"))
            controller.addCardToDeck(command.split(" ")[4]);
        else if (command.matches("validate deck .*"))
            controller.isDeckValid(command.split(" ")[2]);
        else if (command.matches("select deck .*"))
            controller.selectMainDeck(command.split(" ")[2]);
        else if (command.matches("show all decks"))
            controller.showAllDecks();
        else if (command.matches("show deck .*"))
            controller.showDeck(command.split(" ")[2]);
        else if (command.matches("help"))
            help();
        else if (command.matches("exit"))
            view.back();
        else view.printError(ErrorType.INVALID_COMMAND);
    }
}
