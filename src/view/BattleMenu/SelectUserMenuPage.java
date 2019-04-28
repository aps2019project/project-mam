package view.BattleMenu;

import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import Controller.Controller;
import Model.ErrorType;
import view.ConsolePage;
import view.View;

public class SelectUserMenuPage extends ConsolePage {

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
                    view.getPages().push(new GameMoodMenuPage());
                    battleMenuPage.setSecondUser(controller.getSecondUser(userName));
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
