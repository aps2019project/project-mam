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
            if (command.equals("Exit"))
                return EXIT;
            else if (command.matches("create account .*"))
                return ACCOUNT_CREATE_ACCOUNT;
            else if (command.matches("help"))
                return ACCOUNT_HELP;
            else
                return INVALID_COMMAND;
        }
        return INVALID_COMMAND;
    }
}
