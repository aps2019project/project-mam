package view.mainPages;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.stage.Stage;
import view.BattleMenu.BattleMenuPage;
import view.View;


public class MainMenuPage extends Page {
    Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    public MainMenuPage(Stage stage) {
        super(stage);
    }

    @Override
    public void help() {
        view.showHelpForMainMenu();
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
