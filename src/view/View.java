package View;

import Model.ErrorType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class View {
    private static final View VIEW = new View();
    private View(){}
    private Scanner scanner = new Scanner(System.in);

    public static View getInstance(){
        return VIEW;
    }

    private static Deque<ConsolePage> pages = new LinkedList<>();

    public static void start()
    {
        pages.push(new MainMenuPage());
        ConsolePage currentPage = null;
        while (true)
        {
            if (currentPage != pages.peek())
            {
                currentPage = pages.peek();
                if (currentPage == null)
                    break;
                currentPage.help();
            }
            String command = getNewCommand();
            if (command.equalsIgnoreCase("back"))
                back();
            else
                currentPage.handleCommand(command);
        }
    }

    public static void back()
    {
        pages.pop();
    }

    public String getNewCommand(){
        return scanner.nextLine();
    }


















    public void printError(ErrorType type){}
    public void printError(String errorMessage){
        System.out.println(errorMessage);
    }

    public void showHelpForAccountMenu(){
        System.out.print("create account [user name]\nlogin [user name] \nshow leaderboard \nsave \nlogout");
    }
    public void showHelpForMainMenu(){
        System.out.print("Collection \nShop \nBattle \nExit ");
    }
    public void showHelpForCollectionMenu(){
        System.out.print("Exit \nshow \nsearch [card name | item name] \nsave \ncreate deck [deck name] \n" +
                "delete deck[name] \nadd [card id | card id | hero id] to deck [deck name] \nremove deck [deck name]" +
                "validate deck [deck name] \nselect deck [deck name] \nshow all decks \nshow deck [deck name]");
    }
    public void showHelpForBattleMenu(){

    }
    public void showHelpForShopMenu(){
        System.out.print("Exit \nshow collection \nsearch [item name | card name] \nbuy [card name | item name] \n" +
                "sell [card id | card id] \nshow");
    }

}
