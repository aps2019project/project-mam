package Controller;

import Controller.GameController.AudioController;
import Model.enums.ErrorType;
import Model.shop.Shop;
import Model.user.User;
import command.Result;
import command.ServerCommand;
import command.clientCommand.*;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;
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
    public ScrollPane scrollPane;
    public Label showAllLbl;
    public Button showAllBtn;

    private MediaPlayer musicPlayer = AudioController.getInstance().getPlayer("music_tutorial.m4a");

    private Shop shop = Shop.getInstance();


    @FXML
    public void onBackClicked(){
        musicPlayer.stop();
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
        GsonWriter.sendClientCommand(new SearchCmd(searchInStore_tf.getText()), Page.getOutput());
        SResult_lb.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
    }

    @FXML
    public void onShowAllBtnClicked(){
        scrollPane.setVisible(true);
        GsonWriter.sendClientCommand(new ShowAllCmd(), Page.getOutput());
        showAllLbl.setText(GsonReader.getServerCommand(Page.getInput()).getMessage());
    }

    @FXML
    public void onScrollPaneClicked(){
        scrollPane.setVisible(false);
    }

    public void setUpMusic(){
        musicPlayer.play();
        musicPlayer.setAutoPlay(true);
    }


    public void buy(String name) {
        GsonWriter.sendClientCommand(new BuyCmd(name), Page.getOutput());
        ServerCommand serverCommand = GsonReader.getServerCommand(Page.getInput());
        if (serverCommand.getResult() == Result.SUCCESSFUL) {
            SResult_lb.setText(serverCommand.getMessage());
            User.user = serverCommand.getUser();
        }
        else
            SResult_lb.setText(serverCommand.getMessage());
    }

    public void sell(String id) {
        GsonWriter.sendClientCommand(new SellCmd(id), Page.getOutput());
        ServerCommand serverCommand = GsonReader.getServerCommand(Page.getInput());
        if (serverCommand.getResult() == Result.SUCCESSFUL){
            CResult_lb.setText(ErrorType.SUCCESSFUL_SELL.getMessage());
            User.user = serverCommand.getUser();
        } else CResult_lb.setText(serverCommand.getMessage());
    }

}
