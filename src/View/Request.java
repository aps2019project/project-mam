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
                return ACCOUNT_SHOW_LEADERBOARD;
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

        else if (menu.equals("collection Menu")){
            switch (command){
                case "exit":
                    return EXIT;
                case "help":
                    return HELP;
                case "show":
                    return COLLECTION_SHOW;
                case "save":
                    return COLLECTION_SAVE;
                case "show all decks":
                    return COLLECTION_SHOW_ALL_DECKS;
            }
            if (command.matches("search .*"))
                return COLLECTION_SEARCH;
            else if (command.matches("create deck .*"))
                return COLLECTION_CREATE_DECK;
            else if (command.matches("delete deck .*"))
                return COLLECTION_DELETE_DECK;
            else if (command.matches("add /d+ to deck .*"))
                return COLLECTION_ADD;
            else if (command.matches("remove /d+ from deck .*"))
                return COLLECTION_REMOVE;
            else if (command.matches("validate deck .*"))
                return COLLECTION_VALIDATE_DECK;
            else if (command.matches("select deck .*"))
                return COLLECTION_SELECT_DECK;
            else if (command.matches("show deck .*"))
                return COLLECTION_SHOW_DECK;
            else
                return INVALID_COMMAND;
        }

        else if (menu.equals("shop Menu")){
            switch (command){
                case "exit":
                    return EXIT;
                case "help":
                    return HELP;
                case "show collection":
                    return SHOP_SHOW_COLLECTION;
                case "show":
                    return SHOP_SHOW;
            }
            if (command.matches("search collection .*"))
                return SHOP_SEARCH_COLLECTION;
            else if (command.matches("search .*"))
                return SHOP_SEARCH;
            else if (command.matches("buy .*"))
                return SHOP_BUY_CARD;
            else if (command.matches("sell .*"))
                return SHOP_SELL_CARD;
            else
                return INVALID_COMMAND;
        }

        return INVALID_COMMAND;
    }
}
