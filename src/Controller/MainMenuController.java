package Controller;

import Model.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.pages.CollectionMenuPage;
import view.pages.Page;
import view.pages.ShopMenuPage;

public class MainMenuController {
    public Label battleLbl;
    public Label shopLbl;
    public Label collectionLbl;
    public Label saveLbl;
    public Label customLbl;
    public ImageView exit;
    public ImageView logout;

    private User user;

    public MainMenuController() {

    }

    @FXML
    public void onBattleClicked(){
        Page.getPages().push(new BattleMenuPage());
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

    }

    @FXML
    public void onCustomClicked(){

    }

    @FXML
    public void onLogoutClicked(){

    }

    @FXML
    public void onExitClicked(){
        Page.getStage().close();
    }

}
