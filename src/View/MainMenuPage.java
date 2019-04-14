package View;

import static Model.RequestType.*;
import static Model.RequestType.*;

public class MainMenuPage extends ConsolePage {
    @Override
    public void help() {
        System.out.print("Collection \nShop \nBattle \nExit ");
    }

    @Override
    public void handleCommand(String command) {
        switch (command) {
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
        }
    }
}
