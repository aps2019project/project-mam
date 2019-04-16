package view;

import Model.ErrorType;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class View {
    private Scanner scanner = new Scanner(System.in);
    private static final View VIEW = new View();
    private boolean isEnded = false;
    private View(){}

    public static View getInstance(){
        return VIEW;
    }



    private Deque<ConsolePage> pages = new LinkedList<>();

    public void start()
    {
        pages.push(new AccountMenuPage());
        ConsolePage currentPage = null;
        while (!isEnded)
        {
            if (currentPage != pages.peek())
            {
                currentPage = pages.peek();
                if (currentPage == null)
                    break;
                currentPage.showMenu();
            }
            currentPage.handleCommand(getNewCommand());
        }
    }

    public void back()
    {
        pages.pop();
    }

    public void exit(){isEnded = true;}

    public String getNewCommand(){
        return scanner.nextLine().toLowerCase().trim();
    }
    public String getPassword(){
        return scanner.nextLine();
    }

    public Deque<ConsolePage> getPages() {
        return pages;
    }

    public void printError(ErrorType type){
        System.out.println(type.getMessage());
    }

    public void showHelpForAccountMenu(){
        System.out.print("create account [user name]\nlogin [user name] \nshow leaderboard \nsave \nlogout\n");
    }
    public void showHelpForMainMenu(){
        System.out.print("Collection\nShop\nBattle\nExit\n");
    }
    public void showHelpForCollectionMenu(){
        System.out.print("Exit \nshow \nsearch [card name | item name] \nsave \ncreate deck [deck name] \n" +
                "delete deck[name] \nadd [card id | card id | hero id] to deck [deck name] \nremove [card id | card id | hero id] from deck [deck name]" +
                "\nvalidate deck [deck name] \nselect deck [deck name] \nshow all decks \nshow deck [deck name]\n");
    }
    public void showHelpForBattleMenu(){
        System.out.println("1: single player\n2: multi player\n");
    }
    public void showHelpForShopMenu(){
        System.out.print("Exit \nShow collection \nSearch [item name | card name] \nSearch Collection [item name | card name]\nbuy [card name | item name] \n" +
                "sell [card id | card id] \nshow\n");
    }
    public void showHelpForGameMoodMenu(){
        System.out.println("1: kill opponent hero\n2: collect and keep flags\n3: collect half flags\n");
    }
    public void showHelpForGameKindMenu(){
        System.out.println("1: story\n2: custom game\n");
    }
    public void showHelpForMissionMenu(){
        System.out.println("1\n2\n3\n");
    }

    public void show(String string){
        System.out.println(string);
    }
}
