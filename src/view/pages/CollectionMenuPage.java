package view.pages;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.stage.Stage;
import view.View;

public class CollectionMenuPage extends Page {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    public CollectionMenuPage(Stage stage) {
        super(stage);
    }

    @Override
    public void help() {
        view.showHelpForCollectionMenu();
    }


    @Override
    public void handleCommand(String command) {
        if (command.matches("show"))
            controller.showCollection();
        else if (command.matches("search .*"))
            controller.searchInCollection(command.substring(7));
        else if (command.matches("save"))
            controller.saveCollection();
        else if (command.matches("create deck .*"))
            controller.createDeck(command.split(" ")[2]);
        else if (command.matches("delete deck .*"))
            controller.deleteDeck(command.split(" ")[2]);
        else if (command.matches("add \\d+ to deck .*"))
            controller.addCardToDeck(command.split(" ")[1], command.split(" ")[4]);
        else if (command.matches("validate deck .*"))
            controller.validateDeck(command.split(" ")[2]);
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

    @Override
    public void showMenu() {
        view.show("----------<Collection>---------");
    }
}
