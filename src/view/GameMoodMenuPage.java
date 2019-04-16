package view;

import Model.ErrorType;

public class GameMoodMenuPage extends ConsolePage {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForGameMoodMenu();
    }

    @Override
    public void handleCommand(String command) {
        switch (command) {
            case "1":
                battleMenuPage.setGameMood("kill opponent hero");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "2":
                battleMenuPage.setGameMood("collect and keep flags");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "3":
                battleMenuPage.setGameMood("collect half flags");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "back":
                view.back();
                break;
            default:
                view.printError(ErrorType.INVALID_COMMAND);
        }
    }

    @Override
    public void showMenu() {
        view.show("Select Game Mood:");
        view.show("1: kill opponent hero\n2: collect and keep flags\n3: collect half flags\n");
    }
}
