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

        }else if (command.matches("show my minions")){

        }
        else if (command.matches("show opponent minions")){

        }
        else if (command.matches("show card info .*")){

        }
        else if (command.matches("select .*")){  //card

        }
        else if (command.matches("show hand")){

        }
        else if (command.matches("insert (\\S*) in \\(\\d, \\d\\)")){

        }
        else if (command.matches("end turn")){

        }
        else if (command.matches("show collectables")){

        }
        else if (command.matches("select")){  //collectable

        }
        else if (command.matches("show next card")){

        }
        else if (command.matches("enter graveyard")){

        }
        else if (command.matches("help")){

        }
        else view.printError(ErrorType.INVALID_COMMAND);
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
