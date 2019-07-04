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


    public MainMenuController() {

    }

    @FXML
    public void onBattleClicked(){
        AudioController.getInstance().onSelect();
        if (isMainDeckValid()) {
            Page.getPages().push(new BattleMenuPage());
        }
    }

    @FXML
    public void onShopClicked(){
        AudioController.getInstance().onSelect();
        Page.getPages().push(new ShopMenuPage());
    }

    @FXML
    public void onCollectionClicked(){
        AudioController.getInstance().onSelect();
        Page.getPages().push(new CollectionMenuPage());
    }

    @FXML
    public void onSaveClicked(){
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new SaveCmd(), Page.getOutput());
    }

    @FXML
    public void onCustomClicked(){
        AudioController.getInstance().onSelect();
        Page.getPages().push(new CustomMenuPage());
    }

    @FXML
    public void onLogoutClicked(){
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new LogOutCmd(User.user.getName()), Page.getOutput());
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void onExitClicked(){
        AudioController.getInstance().onSelect();
        GsonWriter.sendClientCommand(new LogOutCmd(User.user.getName()), Page.getOutput());
        Page.getStage().close();
    }

    @FXML
    public void onScoreBoardClicked(){
        AudioController.getInstance().onSelect();
        Page.getPages().push(new ScoreBoard());
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
}
