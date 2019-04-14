package view;

import Controller.Controller;


public class MainMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForMainMenu();
    }

    public void handleCommand(String command) {
        /*switch (command) {
            case "enter collection":
                return MAIN_COLLECTION;
            case "enter shop":
                return MAIN_SHOP;
            case "enter battle":
                return MAIN_BATTLE;
            case "exit":
                return EXIT;
            case "help":
                return HELP;
            default:
                return INVALID_COMMAND;
        }*/
    }
}
