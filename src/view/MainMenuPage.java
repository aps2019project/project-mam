package view;

import Controller.Controller;
import Model.ErrorType;


public class MainMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForMainMenu();
    }

    public void handleCommand(String command) {
        switch (command) {
            case "enter collection":
                view.getPages().push(new CollectionMenuPage());
                break;
            case "enter shop":
                view.getPages().push(new ShopMenuPage());
                break;
            case "enter battle":
                view.getPages().push(new BattleMenuPage());
                break;
            case "exit":
                view.exit();
                break;
            case "help":
                view.showHelpForMainMenu();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }
}
