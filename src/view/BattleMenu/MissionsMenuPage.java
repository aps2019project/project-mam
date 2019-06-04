package view.BattleMenu;

import Controller.Controller;
import Model.AI;
import Model.enums.ErrorType;
import view.ConsolePage;
import view.View;

public class MissionsMenuPage extends ConsolePage {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private Controller controller = Controller.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForMissionMenu();
    }

    @Override
    public void handleCommand(String command) {
        switch (command){
            case "1":
                BattleMenuPage.setMission("1");
                controller.setAiMainDeck(1);
                battleMenuPage.setSecondUser(AI.getAI());
                BattleMenuPage.setGameMood("1");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "2":
                BattleMenuPage.setMission("2");
                controller.setAiMainDeck(2);
                battleMenuPage.setSecondUser(AI.getAI());
                BattleMenuPage.setGameMood("2");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "3":
                BattleMenuPage.setMission("3");
                controller.setAiMainDeck(3);
                battleMenuPage.setSecondUser(AI.getAI());
                BattleMenuPage.setGameMood("3");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "back":
                view.back();
                break;
            case "help":
                help();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    @Override
    public void showMenu() {
        view.show("Select Mission:");
        view.show("" +
                "1: Hero Name: Dive Sefid  |  Mood: kill opponent hero\n" +
                "2: Hero Name: Zahhak      |  Mood: collect and keep flags\n" +
                "3: Hero Name: Arash       |  Mood: collect half flags\n");
    }
}
