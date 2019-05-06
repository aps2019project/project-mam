package view;

import Controller.Controller;
import Model.ErrorType;
import view.BattleMenu.BattleMenuPage;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class View {

    private Controller controller = Controller.getInstance();
    private Scanner scanner = new Scanner(System.in);
    private static final View VIEW = new View();
    private boolean isEnded = false;

    private View() {
    }

    public static View getInstance() {
        return VIEW;
    }


    private Deque<ConsolePage> pages = new LinkedList<>();

    public void start() {
        pages.push(new AccountMenuPage());
        ConsolePage currentPage = null;
        while (!isEnded) {
            if (currentPage != pages.peek()) {
                currentPage = pages.peek();
                if (currentPage == null)
                    break;
                currentPage.showMenu();
            }
            if (BattleMenuPage.getInstance().getSecondUser().getName().equalsIgnoreCase("ai") && controller.isAiTurn() && !controller.isEnded())
                currentPage.handleCommand(getNewAiCommand());
            else currentPage.handleCommand(getNewCommand());
        }
    }

    public void back() {
        pages.pop();
    }

    public void exit() {
        isEnded = true;
    }

    public String getNewCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }

    public String getNewAiCommand() {
        return controller.getAiCommand();
    }

    public String getPassword() {
        return scanner.nextLine();
    }

    public Deque<ConsolePage> getPages() {
        return pages;
    }

    public void printError(ErrorType type) {
        System.out.println(type.getMessage());
    }

    public void showHelpForAccountMenu() {
        System.out.print("create account [user name]\nlogin [user name] \nshow leaderboard \nExit\n");
    }

    public void showHelpForMainMenu() {
        System.out.print("Collection\nShop\nBattle\nExit\nSave\nLogout\n");
    }

    public void showHelpForCollectionMenu() {
        System.out.print("Exit \nshow \nsearch [card name | item name] \nsave \ncreate deck [deck name] \n" +
                "delete deck[name] \nadd [card id | card id | hero id] to deck [deck name] \nremove [card id | card id | hero id] from deck [deck name]" +
                "\nvalidate deck [deck name] \nselect deck [deck name] \nshow all decks \nshow deck [deck name]\n");
    }

    public void showHelpForBattleMenu() {
        System.out.println("for select num of player enter its number");
    }

    public void showHelpForShopMenu() {
        System.out.print("Exit \nShow collection \nSearch [item name | card name] \nSearch Collection [item name | card name]\nbuy [card name | item name] \n" +
                "sell [card id | card id] \nshow\n");
    }

    public void showHelpForGameMoodMenu() {
        System.out.println("for playing at single player enter \"Start game [deck name] [mode] [number of flags]\"" +
                "\nfor playing at multi player enter \"Start multiplayer game [mode] [number of flags]\"");
    }

    public void showHelpForGameKindMenu() {
        System.out.println("for select a game kind enter its number");
    }

    public void showHelpForMissionMenu() {
        System.out.println("for select a mission enter its number");
    }

    public void showHelpForSelectUserMenu() {
        System.out.println("for select a user from the account list enter \"Select user [user name]\"");
    }

    public void show(String string) {
        System.out.println(string);
    }
}
