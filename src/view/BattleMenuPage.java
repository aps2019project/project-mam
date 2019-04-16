package view;

import Controller.Controller;
import Model.ErrorType;
import Model.User;

public class BattleMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    private static final BattleMenuPage BATTLE_MENU_PAGE = new BattleMenuPage();
    private String numOfPlayers;
    private String gameKind = null;
    private String gameMood = null;
    private String mission = null;
    private User secondUser = null;
    private User firstUser = controller.getFirstUser();
    private int flags;

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
            view.printError(ErrorType.INVALID_DECK);
            view.back();
            return;
        }
        switch (command) {
            case "1":
                view.getPages().push(new GameKindMenuPage());
                numOfPlayers = "single player";
                break;
            case "2":
                view.getPages().push(new GameMoodMenuPage());
                controller.showUsers();
                view.show("Select User:");
                setSecondUser(getUser2());
                numOfPlayers = "multi player";
                break;
            case "exit":
                view.back();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    public String getNumOfPlayers() {
        return numOfPlayers;
    }

    public String getGameKind() {
        return gameKind;
    }

    public String getGameMood() {
        return gameMood;
    }

    public String getMission() {
        return mission;
    }

    public User getFirstUser() {
        return firstUser;
    }

    public User getSecondUser() {
        return secondUser;
    }

    public int getFlags() {
        return flags;
    }

    public void setNumOfPlayers(String numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void setGameKind(String gameKind) {
        this.gameKind = gameKind;
    }

    public void setGameMood(String gameMood) {
        this.gameMood = gameMood;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public void setSecondUser(User secondUser) {
        this.secondUser = secondUser;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public User getUser2() {
        String command = view.getNewCommand();
        if (command.matches("select user .+")) {
            String userName = command.split(" ")[2];
            if (controller.getSecondUser(userName) != null) {
                return controller.getSecondUser(userName);
            } else {
                view.back();
                view.back();
                view.getPages().push(new BattleMenuPage());
                return null;
            }
        } else {
            view.printError(ErrorType.INVALID_COMMAND);
            view.back();
            view.back();
            view.getPages().push(new BattleMenuPage());
            return null;
        }
    }
}
