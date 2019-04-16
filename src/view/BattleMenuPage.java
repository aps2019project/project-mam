package view;

import Controller.Controller;
import Model.ErrorType;

public class BattleMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();
    private String numOfPlayers;
    private String gameKind = null;
    private String gameMood = null;
    private String mission = null;
    private String secondPlayer = null;

    @Override
    public void help() {
        view.showHelpForBattleMenu();
    }


    @Override
    public void handleCommand(String command) {
        if (!controller.isMainDeckValid()) {
            view.printError(ErrorType.INVALID_DECK);
            view.back();
            return;
        }
        numOfPlayers(command);
    }

    public void numOfPlayers(String command) {

        numOfPlayers:
        while (true) {
            switch (command) {
                case "1":
                    numOfPlayers = "single player";
                    secondPlayer = "AI";
                    gameKind();
                    break numOfPlayers;
                case "2":
                    numOfPlayers = "multi player";
                    controller.showUsers();
                    command = view.getNewCommand();
                    if (!controller.isMainDeckValid(command.split(" ")[2])){
                        view.printError(ErrorType.INVALID_DECK_2);
                    }
                    secondPlayer = command.split(" ")[2];
                    gameMood();
                    break numOfPlayers;
                default:
                    view.printError(ErrorType.INVALID_COMMAND);
            }
            command = view.getNewCommand();
        }
    }

    public void gameKind() {
        showKinds();
        gameKind:
        while (true) {
            String command = view.getNewCommand();
            switch (command) {
                case "1":
                    gameKind = "story";
                    mission();
                    break gameKind;
                case "2":
                    gameKind = "custom game";
                    controller.showAllDecks();
                    showMoods();
                    gameMood();
                    break gameKind;
                default:
                    view.printError(ErrorType.INVALID_COMMAND);
            }
        }
    }

    public void gameMood() {
        showMoods();
        gameMood:
        while (true) {
            String command = view.getNewCommand();
            if (command.matches("start game (.*) (\\d)( \\d+)?")
                    || command.matches("start multiplayer game (\\d)( \\d+)?"))
                switch (command.split(" ")[3]) {
                    case "1":
                        gameMood = "kill opponent hero";
                        break gameMood;
                    case "2":
                        gameMood = "collect and keep flags";
                        break gameMood;
                    case "3":
                        gameMood = "collect half flags";
                        break gameMood;
                    default:
                        view.printError(ErrorType.INVALID_COMMAND);
                }
            else  view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    public void mission() {
        showMission();
        mission:
        while (true) {
            String command = view.getNewCommand();
            switch (command) {
                case "1":
                    mission = "1";
                    break mission;
                case "2":
                    mission = "2";
                    break mission;
                case "3":
                    mission = "3";
                    break mission;
                default:
                    view.printError(ErrorType.INVALID_COMMAND);
            }
        }
    }
    public void showMission(){
        view.show("1\n2\n3\n");
    }

    public void showFirstMenu(){
        view.show("1: single player\n2: multi player\n");
    }

    public void showKinds(){
        view.show("1: story\n2: custom game");
    }

    public void showMoods(){
        view.show("1: kill opponent hero\n2: collect and keep flags\n3: collect half flags\n");
    }
}
