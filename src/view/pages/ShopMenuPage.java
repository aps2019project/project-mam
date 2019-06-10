package view.pages;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.View;

import java.io.FileInputStream;
import java.io.IOException;

public class ShopMenuPage extends Page {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();


    @Override
    public void start() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/MainMenu2.fxml"));
        Pane root = fxmlLoader.load();


        setBackGround(root, "resources/codex/chapter22_background@2x.jpg");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        /*stage.setFullScreen(true);
        stage.setFullScreenExitHint("Welcome");*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ShopMenuPage(Stage stage) {
        super(stage);
    }

    @Override
    public void help() {
        view.showHelpForShopMenu();
    }

    public void handleCommand(String command) {

        if (command.matches("show collection"))
            controller.showCollection();
        else if (command.matches("search collection .*"))
            controller.searchInCollection(command.substring(18));
        else if (command.matches("search .*"))
            controller.searchInShop(command.substring(7));
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
