package view.BattleMenu;

import Controller.Controller;
import view.ConsolePage;
import view.View;

public class SelectCardMenu extends ConsolePage {

    private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        super.help();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("move to \\d \\d")){
            controller.moveCard(command.split(" ")[2], command.split(" ")[3]);
            view.back();
        }
        else if (command.matches("attack \\d+")){
            controller.attack(command.split(" ")[1]);
            view.back();
        }
        else if (command.matches("attack combo \\d+( \\d+)+")){
            view.back();
        }
        else if (command.matches("use special power \\d \\d")){
            view.back();
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
        StringBuilder menu = new StringBuilder();
        menu.append("-----<").append(controller.getGame().getCurrentCard().getId()).append(" selected>-----");
        view.show(menu.toString());
    }
}
