package Controller;

import Controller.GameController.AudioController;
import Model.enums.ErrorType;
import command.clientCommand.LogOutCmd;
import command.clientCommand.SaveCmd;
import gson.GsonWriter;
import Model.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import view.BattleMenu.BattleMenuPage;
import view.pages.*;

import java.io.IOException;

public class MainMenuController {
    public Label battleLbl;
    public Label shopLbl;
    public Label collectionLbl;
    public Label saveLbl;
    public Label customLbl;
    public Label label;
    public ImageView exit;
    public ImageView logout;
    public Label scoreBoard;
    public Label history;
    private MediaPlayer musicPlayer = AudioController.getInstance().getPlayer("mainmenu_v2c_looping.m4a");


    public MainMenuController() {

    }

    @FXML
    public void onBattleClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        if (isMainDeckValid()) {
            Page.getPages().push(new BattleMenuPage());
        }
    }

    @FXML
    public void onShopClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        Page.getPages().push(new ShopMenuPage());
    }

    @FXML
    public void onHistoryClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        Page.getPages().push(new HistoryPage());
    }
    @FXML
    public void onCollectionClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        Page.getPages().push(new CollectionMenuPage());
    }

    @FXML
    public void onSaveClicked(){
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new SaveCmd(User.user), Page.getOutput());
    }

    @FXML
    public void onCustomClicked(){
        AudioController.getInstance().onSelect();
        Page.getPages().push(new CustomMenuPage());
    }

    @FXML
    public void onLogoutClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new LogOutCmd(User.user.getName()), Page.getOutput());
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void onExitClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new LogOutCmd(User.user.getName()), Page.getOutput());
        Page.getStage().close();
    }

    @FXML
    public void onScoreBoardClicked(){
        turnOffMusic();
        AudioController.getInstance().onSelect();
        Page.getPages().push(new ScoreBoard());
    }

    public void setUpMusic(){
        musicPlayer.play();
        musicPlayer.setAutoPlay(true);
    }

    public boolean isMainDeckValid() {
        if (User.user.getMainDeck() != null) {
            if (User.user.getCollection().isValidMainDeck()) {
                return true;
            } else {
                label.setText(ErrorType.INVALID_DECK.getMessage());
                return false;
            }
        }
        label.setText(ErrorType.NOT_SELECT_MAIN_DECK.getMessage());
        return false;
    }

    public void turnOffMusic(){
        musicPlayer.stop();
    }
}
