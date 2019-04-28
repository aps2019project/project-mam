package view.BattleMenu;

import Controller.Controller;
import Model.ErrorType;
import Model.User;
import view.ConsolePage;
import view.View;

public class BattleMenuPage extends ConsolePage {
    private Controller controller = Controller.getInstance();
    View view = View.getInstance();

    private static final BattleMenuPage BATTLE_MENU_PAGE = new BattleMenuPage();
    private static String numOfPlayers = null;
    private static String gameKind = null;
    private static String gameMood = null;
    private static String mission = null;
    private User secondUser = null;
    private User firstUser = controller.getFirstUser();
    private static int flags;

    public static BattleMenuPage getInstance() {
        return BATTLE_MENU_PAGE;
    }

    @Override
    public void help() {
        view.showHelpForBattleMenu();
    }

    @Override
    public void showMenu() {
        view.show("----------<Battle Menu>---------");
        view.show("1: single player\n2: multi player\n");
    }

    @Override
    public void handleCommand(String command) {
        if (!controller.isMainDeckValid()) {
            view.back();
            return;
        }
        switch (command) {
            case "1":
                setNumOfPlayers("single player");
                numOfPlayers = "single player";
                view.getPages().push(new GameKindMenuPage());
                break;
            case "2":
                setNumOfPlayers("multi player");
                numOfPlayers = "multi player";
                view.getPages().push(new SelectUserMenuPage());
                break;
            case "exit":
                view.back();
                break;
            case "help":
                help();
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    public static String getNumOfPlayers() {
        return numOfPlayers;
    }

    public static String getGameKind() {
        return gameKind;
    }

    public static String getGameMood() {
        return gameMood;
    }

    public static String getMission() {
        return mission;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public static int getFlags() {
        return flags;
    }

    public static void setNumOfPlayers(String numOfPlayers) {
        BattleMenuPage.numOfPlayers = numOfPlayers;
    }

    public static void setGameKind(String gameKind) {
        BattleMenuPage.gameKind = gameKind;
    }

    public static void setGameMood(String gameMood) {
        BattleMenuPage.gameMood = gameMood;
    }

    public static void setMission(String mission) {
        BattleMenuPage.mission = mission;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public static void setFlags(int flags) {
        BattleMenuPage.flags = flags;
    }
}
