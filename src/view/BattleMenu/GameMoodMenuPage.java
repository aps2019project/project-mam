package view.BattleMenu;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.stage.Stage;
import view.View;
import view.mainPages.Page;

public class GameMoodMenuPage extends Page {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();
    private Controller controller = Controller.getInstance();

    public GameMoodMenuPage(Stage stage) {
        super(stage);
    }

    @Override
    public void help() {
        view.showHelpForGameMoodMenu();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("start game .*")){
            controller.setSecondDeck(controller.getFirstUser().getCollection().getDeck(command.split(" ")[2]));
        }
        if (command.matches("start game (\\S*) \\d( \\d+)?")
                || command.matches("start multiplayer game \\d( \\d+)?")) {
            if (controller.isDeckValid(command.split(" ")[2])
                    || command.split(" ")[1].equalsIgnoreCase("multiplayer")) {
                switch (command.split(" ")[3]) {
                    case "1":
                       // controller.selectMainDeck();
                        BattleMenuPage.setGameMood("1");
                        view.getPages().push(new MainBattleMenuPage());
                        break;
                    case "2":
                        BattleMenuPage.setGameMood("2");
                        BattleMenuPage.setFlags(1);
                        view.getPages().push(new MainBattleMenuPage());
                        break;
                    case "3":
                        BattleMenuPage.setGameMood("3");
                        BattleMenuPage.setFlags(Integer.parseInt(command.split(" ")[4]));
                        view.getPages().push(new MainBattleMenuPage());
                        break;
                    default:
                        view.printError(ErrorType.INVALID_MOOD_NUM);
                }
            } else view.printError(ErrorType.INVALID_DECK);
        } else if (command.equalsIgnoreCase("help"))
            help();
        else if (command.equalsIgnoreCase("back"))
            view.back();
        else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        switch (BattleMenuPage.getNumOfPlayers()) {
            case "single player":
                view.show("Select Game Mood and Deck:");
                view.show("Moods:");
                view.show("\t1: kill opponent hero\n\t2: collect and keep flags\n\t3: collect half flags\n");
                view.show("Decks:");
                controller.showAllDecks();
                break;
            case "multi player":
                view.show("Select Game Mood:");
                view.show("Moods:");
                view.show("\t1: kill opponent hero\n\t2: collect and keep flags\n\t3: collect half flags\n");
                break;
            default:
                view.show("....");
        }
    }
}
