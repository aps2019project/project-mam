package view.BattleMenu;

import Controller.Controller;
import Model.ErrorType;
import Model.Game;
import view.ConsolePage;
import view.View;

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
        if (command.matches("game info")){
            controller.showGameInfo();
        }else if (command.matches("show my minions")){
            controller.showMyMinions();
        }
        else if (command.matches("show opponent minions")){
            controller.showOpMinions();
        }
        else if (command.matches("show card info .*")){
            controller.showCardInfo(command.split(" ")[3]);
        }
        else if (command.matches("select .*")){  //card
            view.getPages().push(new SelectCardMenu());
            controller.selectCard(command.split(" ")[1]);
        }
        else if (command.matches("show hand")){
            controller.showHand();
        }
        else if (command.matches("insert (\\S*) in \\d \\d")){
            controller.insertCard(command.split("")[1], command.split(" ")[3], command.split(" ")[4]);
        }
        else if (command.matches("end turn")){
            controller.endTurn();
        }
        /*else if (command.matches("show collectables")){

        }*/
        /*else if (command.matches("select .*")){  //collectable

        }*/
        else if (command.matches("show next card")){
            controller.showNextCard();
        }
        else if (command.matches("enter graveyard")){
            view.getPages().push(new GraveyardMenu());
        }
        else if (command.matches("help")){
            help();
        }
        else if (command.matches("exit")){
            view.back();
        }
        else view.printError(ErrorType.INVALID_COMMAND);
    }

    @Override
    public void showMenu() {
        controller.setGame(game);
        view.show("----------< BATTLE >---------");
        view.show(BattleMenuPage.getNumOfPlayers());
        view.show(BattleMenuPage.getGameKind());
        view.show(BattleMenuPage.getGameMood());
        view.show(BattleMenuPage.getMission());
    }
}
