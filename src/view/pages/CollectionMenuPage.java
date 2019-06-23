package view.pages;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CollectionMenuPage extends Page {
    //Controller controller = Controller.getInstance();
    View view = View.getInstance();

    private static Pane root = new Pane();
    private CollectionController controller;

    public CollectionMenuPage(Stage stage) {
        super(stage);
    }

    public CollectionMenuPage() {
        start();
    }



    @Override
    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Collection.fxml"));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();


            initializeImage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() throws FileNotFoundException {
        setBackGround(root, "resources/codex/chapter7_background@2x.jpg");
        setBackGround(controller.search_lb, "resources/ui/button_primary_middle_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }


    @Override
    public void help() {
        view.showHelpForCollectionMenu();
    }


    /*@Override
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
    }*/

    @Override
    public void showMenu() {
        view.show("----------<Collection>---------");
    }
}
