package view;

import Model.ErrorType;

public class GameKindMenuPage extends ConsolePage {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForGameKindMenu();
    }

    @Override
    public void handleCommand(String command) {
        switch (command){
            case "1":
                battleMenuPage.setGameKind("story");
                view.getPages().push(new MissionsMenuPage());
                break;
            case "2":
                battleMenuPage.setGameKind("custom game");
                view.getPages().push(new GameMoodMenuPage());
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
        view.show("Select Game Kind:");
        view.show("1: story\n2: custom game\n");
    }
}
