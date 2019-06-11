package Controller;

import Model.enums.ErrorType;
import Model.shop.Shop;
import Model.user.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.pages.Page;

public class ShopController {

    public Label collection_lb;
    public Label store_lb;
    public TextField searchInCollection_tf;
    public TextField searchInStore_tf;
    public Label CResult_lb;
    public Label SResult_lb;
    public Button CSearch_btn;
    public Button SSearch_btn;
    public Button sell_btn;
    public Button buy_btn;
    public ImageView back = new ImageView();



    private Shop shop = Shop.getInstance();


    @FXML
    public void onBackClicked(){
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void onSellClicked(){
        sell(CResult_lb.getText().split(" ")[2]);
    }

    @FXML
    public void onBuyClicked(){
        buy(searchInStore_tf.getText());
    }

    @FXML
    public void onCSearchClicked(){
        CollectionController.searchInCollection(searchInCollection_tf.getText(), CResult_lb);
    }

    @FXML
    public void onSSearchClicked(){
        searchInShop(searchInStore_tf.getText());
    }



    public void searchInShop(String name) {
        if (shop.searchCard(name)) {
            SResult_lb.setText(shop.getCardInfo(name));
        } else if (shop.searchItem(name)) {
            SResult_lb.setText(shop.getItemInfo(name));
        } else SResult_lb.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage());
    }

    public void buy(String name) {
        if (shop.cardNameIsAvailable(name)) {
            if (shop.priceIsEnough(shop.getCardPrice(name), User.user)) {
                shop.buyCard(name, User.user);
                SResult_lb.setText(ErrorType.SUCCESSFUL_BUY.getMessage());
            } else SResult_lb.setText(ErrorType.MONEY_IS_NOT_ENOUGH.getMessage());
        } else if (shop.itemNameIsAvailable(name)) {
            if (shop.isPossibleToAddItem(User.user)) {
                if (shop.priceIsEnough(shop.getItemPrice(name), User.user)) {
                    shop.buyItem(name, User.user);
                    SResult_lb.setText(ErrorType.SUCCESSFUL_BUY.getMessage());
                } else SResult_lb.setText(ErrorType.MONEY_IS_NOT_ENOUGH.getMessage());
            } else SResult_lb.setText(ErrorType.THREE_ITEM.getMessage());
        } else SResult_lb.setText(ErrorType.UNAVAILABLE_CARD_OR_ITEM.getMessage());
    }

    public void sell(String id) {
        int ID = Integer.parseInt(id);
        if (shop.sellCard(ID, User.user)) {
            CResult_lb.setText(ErrorType.SUCCESSFUL_SELL.getMessage());
        } else if (shop.sellItem(ID, User.user)) {
            CResult_lb.setText(ErrorType.SUCCESSFUL_SELL.getMessage());
        } else CResult_lb.setText(ErrorType.NOT_FOUND_CARD_OR_ITEM.getMessage());
    }

}
