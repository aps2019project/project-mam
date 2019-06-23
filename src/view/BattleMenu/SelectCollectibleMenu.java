package view.BattleMenu;

import view.pages.Page;

public class SelectCollectibleMenu extends Page {

    private Controller controller = Controller.getInstance();
    private BattleMenuPage battleMenuPage = BattleMenuPage.getInstance();
    private View view = View.getInstance();

    @Override
    public void help() {
        super.help();
    }

    @Override
    public void handleCommand(String command) {
        if (command.matches("show info")){
            controller.showCollectibleInfo();
        }
        else if (command.matches("use")){
            controller.useCollectible();
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
        menu.append("-----<").append(controller.getGame().getCurrentItem().getId()).append(" selected>-----");
        view.show(menu.toString());
    }
}
