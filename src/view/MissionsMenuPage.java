package view;

import Model.ErrorType;

public class MissionsMenuPage extends ConsolePage {

    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        view.showHelpForMissionMenu();
    }

    @Override
    public void handleCommand(String command) {
        switch (command){
            case "1":
                battleMenuPage.setMission("1");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "2":
                battleMenuPage.setMission("1");
                view.getPages().push(new MainBattleMenuPage());
                break;
            case "3":
                battleMenuPage.setMission("3");
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
        view.show("Select Mission:");
        view.show("1:  Hero Name: Dive Sefid | Mood: kill opponent hero\n" +
                "2: Hero Name: Zahhak | Mood: collect and keep flags\n" +
                "3: Hero Name: Arash | Mood: collect half flags\n");
    }
}
