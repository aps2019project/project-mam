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
    public Label shopLbl;

    @FXML
    public void onRefreshClicked(){
        refreshList();
    }

    public void refreshList(){
        StringBuilder info = new StringBuilder();
        info.append("Users:\n");
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet()) {
            info.append("\n").append("client name:  ").append(entry.getKey()).append("  |     on port:  ")
                    .append(entry.getValue().getClient().getPort()).append("  |     game requested:  ")
                    .append(entry.getValue().isRequested());
        }
        clientLbl.setText(info.toString());
        shopLbl.setText(Shop.getInstance().show());
    }
}
