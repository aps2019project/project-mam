package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import server.ClientHandler;
import server.Server;

import java.util.Map;

public class ServerCtrl {
    public Label label;
    public Button refresh;

    @FXML
    public void onRefreshClicked(){
        refreshList();
    }

    public void refreshList(){
        StringBuilder info = new StringBuilder();
        for (Map.Entry<String, ClientHandler> entry : Server.getClients().entrySet()) {
            info.append("client name : ").append(entry.getValue().getUser().getName()).append("\n");
        }
        label.setText(info.toString());
    }
}
