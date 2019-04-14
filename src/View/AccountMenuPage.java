package View;

import Controller.Controller;

import static Model.RequestType.*;
import static Model.RequestType.INVALID_COMMAND;

public class AccountMenuPage extends ConsolePage{
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    public void help(){
        view.showHelpForAccountMenu();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("create account .*"))
            controller.createAccount();
        else if (command.matches("save"))
            controller.saveAccount();
        else if (command.matches("logout"))
            controller.logoutAccount();
        else if (command.matches("login .*"))
            controller.loginAccount();
        else if (command.matches("show leaderboard"))
            controller.showLeaderBoard();
        else if (command.matches("help"))
            view.showHelpForAccountMenu();
        else


    }
}
