package View;

import static Model.RequestType.*;
import static Model.RequestType.INVALID_COMMAND;

public class AccountMenuPage extends ConsolePage{

    @Override
    public void help() {
        System.out.print("create account [user name]\nlogin [user name] \nshow leaderboard \nsave \nlogout");
    }

    @Override
    public  handleCommand(String command) {
        if (command.equals("exit"))
            return EXIT;
        if (command.matches("create account .*"))
            return ACCOUNT_CREATE_ACCOUNT;
        else if (command.matches("save"))
            return ACCOUNT_SAVE;
        else if (command.matches("logout"))
            return ACCOUNT_LOGOUT;
        else if (command.matches("login .*"))
            return ACCOUNT_LOGIN;
        else if (command.matches("show leaderboard"))
            return ACCOUNT_SHOW_LEADERBOARD;
        else if (command.matches("help"))
            return HELP;
        else
            return INVALID_COMMAND;
    }
}
