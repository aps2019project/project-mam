package view;

import Controller.Controller;
import Model.Game;

public class MainBattleMenuPage extends ConsolePage {

    private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    private Game game = new Game(battleMenuPage.getFirstUser(), battleMenuPage.getSecondUser(),
            BattleMenuPage.getGameMood(), BattleMenuPage.getGameKind(), 0);



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
        view.show(BattleMenuPage.getNumOfPlayers());
        view.show(BattleMenuPage.getGameKind());
        view.show(BattleMenuPage.getGameMood());
        view.show(BattleMenuPage.getMission());
    }
}
