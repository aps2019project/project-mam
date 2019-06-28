package Controller;

import Model.enums.ErrorType;
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


    public MainMenuController() {

    }

    @FXML
    public void onBattleClicked(){
        if (isMainDeckValid()) {
            Page.getPages().push(new BattleMenuPage());
        }
    }

    @FXML
    public void onShopClicked(){
        Page.getPages().push(new ShopMenuPage());
    }

    @FXML
    public void onCollectionClicked(){
        Page.getPages().push(new CollectionMenuPage());
    }

    @FXML
    public void onSaveClicked(){
        try {
            GsonWriter.writeUser(User.user);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onCustomClicked(){
        Page.getPages().push(new CustomMenuPage());
    }

    @FXML
    public void onLogoutClicked(){
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void onExitClicked(){
        Page.getStage().close();
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
