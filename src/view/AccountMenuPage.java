package view;

import Controller.Controller;
import Model.ErrorType;

public class AccountMenuPage extends ConsolePage{
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    public void help(){
        view.showHelpForAccountMenu();
    }


    public void handleCommand(String command) {
        if (command.matches("create account .*"))
            controller.createAccount(command);
        else if (command.matches("save"))
            controller.saveAccount(command);
        else if (command.matches("logout"))
            controller.logoutAccount(command);
        else if (command.matches("login .*"))
            controller.loginAccount(command);
        else if (command.matches("show leaderboard"))
            controller.showLeaderBoard(command);
        else if (command.matches("help"))
            view.showHelpForAccountMenu();
        else
            view.printError(ErrorType.INVALID_COMMAND);

    }

    @Override
    public void showMenu() {
        view.show("----------<Account>---------");
    }
}
