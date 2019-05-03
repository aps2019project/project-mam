package view;

import Controller.Controller;
import Model.ErrorType;

public class AccountMenuPage extends ConsolePage{
    private Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    public void help(){
        view.showHelpForAccountMenu();
    }


    public void handleCommand(String command) {
        if (command.matches("create account .*"))
            controller.createAccount(command);
        else if (command.matches("login .*"))
            controller.loginAccount(command);
        else if (command.matches("show leaderboard"))
            controller.showLeaderBoard();
        else if (command.matches("exit"))
            view.exit();
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
