package View;

import Model.ErrorType;
import Model.RequestType;

import java.util.Scanner;

import static Model.RequestType.*;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    private ErrorType error = null;
    private String command;


    public void getNewCommand() {
        this.command = scanner.nextLine();
        command = command.toLowerCase().trim();
    }

    public ErrorType getError() {
        return error;
    }

    public void setError(ErrorType error) {
        this.error = error;
    }

    public RequestType getRequestType(String menu){
        if (menu.equals("account")){
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
                return ACCOUNT_SHOWlEADERBOARD;
            else if (command.matches("help"))
                return HELP;
            else
                return INVALID_COMMAND;
        }
        else if (menu.equals("main Menu")){
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
        return INVALID_COMMAND;
    }
}
