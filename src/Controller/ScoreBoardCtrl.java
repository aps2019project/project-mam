package Controller;

import Model.game.Game;
import command.ServerCommand;
import command.clientCommand.RequestLiveCmd;
import command.clientCommand.ScoreBoardCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.pages.Page;

public class ScoreBoardCtrl {

    public ImageView back;
    public ScrollPane scoreBoard;
    public Label scoreBoardLb;
    public ScrollPane games;
    public Label gamesLb;

    public Button show;
    public TextField gameNum;

    public void setShow() {
        GsonWriter.sendClientCommand(new RequestLiveCmd(Integer.parseInt(gameNum.getText())), Page.getOutput());
        ServerCommand command = GsonReader.getServerCommand(Page.getInput());
        showGame(command.getGame());
    }

    private void showGame(Game game){
        /*BattleMenuPage.setFlags(game.getFlagCount());
        BattleMenuPage.setGameMood(game.getMode());
        BattleMenuPage.setGameKind(game.getKind());
        BattleMenuPage.setSecondUser(game.getSecondUser());
        BattleMenuPage.setFirstUser(game.getFirstUser());
        BattleMenuPage.setBaseTurn(game.getBaseTurn());*/
        BattleMenuPage.createLiveGame(game);
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void initialize(){
        GsonWriter.sendClientCommand(new ScoreBoardCmd(), Page.getOutput());
        scoreBoardLb.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
        gamesLb.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
    }
}
