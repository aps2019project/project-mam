package view;

import Controller.Controller;
import Model.ErrorType;

public class BattleMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();

    private static final BattleMenuPage BATTLE_MENU_PAGE = new BattleMenuPage();
    private String numOfPlayers;
    private String gameKind = null;
    private String gameMood = null;
    private String mission = null;
    private String secondPlayer = null;

    public static BattleMenuPage getInstance(){
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

    public String getSecondPlayer() {
        return secondPlayer;
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

    public void setSecondPlayer(String secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public void showMission() {
        view.show("1\n2\n3\n");
    }

    public void showFirstMenu() {
        view.show("1: single player\n2: multi player\n");
    }

    public void showKinds() {
        view.show("1: story\n2: custom game");
    }

    public void showMoods() {
        view.show("1: kill opponent hero\n2: collect and keep flags\n3: collect half flags\n");
    }
}
