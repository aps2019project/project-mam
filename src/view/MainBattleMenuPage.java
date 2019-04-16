package view;

import Controller.Controller;
import Model.Game;

public class MainBattleMenuPage extends ConsolePage {

    private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    private Game game = new Game(battleMenuPage.getFirstUser(), battleMenuPage.getSecondUser(),
            battleMenuPage.getGameMood(), battleMenuPage.getGameKind(), 0);



    @Override
    public void help() {
        super.help();
    }

    @Override
    public void handleCommand(String command) {

    }

    @Override
    public void showMenu() {
        view.show("----------< BATTLE >---------");
    }
}
