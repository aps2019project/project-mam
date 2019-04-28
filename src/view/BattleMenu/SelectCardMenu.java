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

        }
        else if (command.matches("attack \\d+")){

        }
        else if (command.matches("attack combo \\d+( \\d+)+")){

        }
        else if (command.matches("use special power \\d \\d")){

        }
        else if (command.matches("select .+")){
            view.getPages().push(new SelectCollectableMenu());
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
        view.show("----------<Select Card>---------");
    }
}
