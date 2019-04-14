package view;

import Controller.Controller;

public class BattleMenuPage extends ConsolePage {
    Controller controller = Controller.getInstance();
    View view = View.getInstance();
    @Override
    public void help() {
        view.showHelpForBattleMenu();
    }


    @Override
    public void handleCommand(String command) {
        super.handleCommand(command);
    }
}
