package Controller;

import Model.shop.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.ClientHandler;
import server.Server;

import java.util.Map;

public class ServerCtrl {
    public Label clientLbl;
    public Button refresh;
    public Button shop;
    public Label shopLbl;

    @FXML
    public void onRefreshClicked(){
        refreshList();
    }
    @FXML
    public void onShopClicked(){
        shopLbl.setText(Shop.getInstance().show());
    }

    public void refreshList(){
        StringBuilder info = new StringBuilder();
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet()) {
            info.append("client name : ").append(entry.getValue().getUser().getName()).append("\n");
        }
        clientLbl.setText(info.toString());
    }
}
