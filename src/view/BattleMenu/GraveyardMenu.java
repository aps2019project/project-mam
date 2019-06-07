package view.BattleMenu;

import Controller.Controller;
import view.Page;
import view.View;

public class GraveyardMenu extends Page {

    private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        super.help();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("show info \\d+")){
            controller.showInfoInGraveyard(command.split(" ")[2]);
        }
        else if (command.matches("show cards")){
            controller.showCardsInGraveyard();
        }
        else if (command.matches("help")){
            help();
        }
        else if (command.matches("exit")){
            view.back();
        }
    }

    @Override
    public void showMenu() {
        view.show("----------<Graveyard>---------");
    }
}
