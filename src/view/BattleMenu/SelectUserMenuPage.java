package view.BattleMenu;

import Controller.Controller;
import Model.enums.ErrorType;
import view.View;
import view.pages.Page;

public class SelectUserMenuPage extends Page {

    Controller controller = Controller.getInstance();
    View view = View.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();

    @Override
    public void help() {
        view.showHelpForSelectUserMenu();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("select user .*")) {
            String userName = command.split(" ")[2];
            if (controller.isUserNameValid(userName)) {
                if (controller.isMainDeckValid(userName)) {
                    battleMenuPage.setSecondUser(controller.getSecondUser(userName));
                    view.getPages().push(new GameMoodMenuPage(stage));
                } else view.printError(ErrorType.INVALID_DECK_2);
            } else view.printError(ErrorType.INVALID_USERNAME);
        }else if (command.equalsIgnoreCase("help")) {
            help();
        }else if (command.equalsIgnoreCase("back")){
            view.back();
        }
        else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        controller.showUsers();
        view.show("Select Second User:");
    }
}
