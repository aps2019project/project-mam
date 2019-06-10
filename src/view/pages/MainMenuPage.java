package view.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.omg.CORBA.MARSHAL;
import view.BattleMenu.BattleMenuPage;
import Controller.*;
import Model.enums.ErrorType;
import view.View;

import java.io.FileInputStream;
import java.io.IOException;


public class MainMenuPage extends Page {
    Controller controller = Controller.getInstance();
    private View view = View.getInstance();


    public MainMenuPage(Stage stage) {
        super(stage);
    }

    public MainMenuPage() {
        start();
    }

    @Override
    public void help() {
        view.showHelpForMainMenu();
    }


    public void start() {
        try {
            /*root = FXMLLoader.load(getClass().getResource("../layout/MainMenu.fxml"));
            FXMLLoader.load(getClass().getResource("../layout/MainMenu.fxml"));*/


            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/MainMenu2.fxml"));

            Pane root = fxmlLoader.load();
            MainMenu2Controller mainMenuController = fxmlLoader.getController();


            //mainMenuController.battle.setImage(new Image(new FileInputStream("resources/ui/button_primary_right_glow@2x.png")));
            /*mainMenuController.shop.setImage(new Image(new FileInputStream("resources/ui/button_primary_right.png")));
            mainMenuController.collection.setImage(new Image(new FileInputStream("resources/ui/button_primary_right.png")));
            mainMenuController.custom.setImage(new Image(new FileInputStream("resources/ui/button_primary_right.png")));*/
            mainMenuController.logout.setImage(new Image(new FileInputStream("resources/ui/button_close.png")));
            mainMenuController.exit.setImage(new Image(new FileInputStream("resources/ui/utility_menu/settings.png")));


            setBackGround(root, "resources/codex/chapter22_background@2x.jpg");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.setFullScreenExitHint("Welcome");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void handleCommand(String command) {
        switch (command) {
            case "1": //Collection
                view.getPages().push(new CollectionMenuPage(stage));
                break;
            case "2": //Shop
                view.getPages().push(new ShopMenuPage(stage));
                break;
            case "3": //Battle
                view.getPages().push(new BattleMenuPage());
                break;
            case "4": //Exit
                view.exit();
                break;
            case "5": //save
                controller.saveAccount();
                break;
            case "6": //logout
                controller.logoutAccount();
                view.back();
                break;
            case "7": //Help
                view.showHelpForMainMenu();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    @Override
    public void showMenu() {
        view.show("----------<Main Menu>---------");
        view.show("1: Collection\n2: Shop\n3: Battle\n4: Exit\n5: Save\n6: Logout\n7: Help");
    }
}
