package view;

import Controller.Controller;
import Model.ErrorType;

public class GameMoodMenuPage extends ConsolePage {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();
    Controller controller = Controller.getInstance();

    @Override
    public void help() {
        super.help();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("start game (\\S*) \\d( \\d+)?")
                || command.matches("start multiplayer game \\d( \\d+)?")) {
            if (controller.isDeckValid(command.split(" ")[2])
                    || command.split(" ")[1].equalsIgnoreCase("multiplayer")) {
                switch (command.split(" ")[3]) {
                    case "1":
                        view.getPages().push(new MainBattleMenuPage());
                        battleMenuPage.setGameMood("1");
                        break;
                    case "2":
                        view.getPages().push(new MainBattleMenuPage());
                        battleMenuPage.setGameMood("2");
                        battleMenuPage.setFlags(1);
                        break;
                    case "3":
                        view.getPages().push(new MainBattleMenuPage());
                        battleMenuPage.setGameMood("3");
                        battleMenuPage.setFlags(Integer.parseInt(command.split(" ")[4]));
                        break;
                    case "help":
                        help();
                    case "back":
                        view.back();
                    default:
                        view.printError(ErrorType.INVALID_MOOD_NUM);
                }
            } else view.printError(ErrorType.INVALID_DECK);
        } else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        view.show("Select Game Mood and Deck:");
        view.show("1: kill opponent hero\n2: collect and keep flags\n3: collect half flags\n");
        controller.showAllDecks();
    }
}
