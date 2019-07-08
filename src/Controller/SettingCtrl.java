package Controller;

import config.Config;
import gson.GsonWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.pages.Page;

public class SettingCtrl {

    public ImageView back;

    public TextField speed;
    public TextField time;
    public TextField port;
    public TextField ip;

    public Button defaultBtn;

    public void init(){
        speed.setText(String.valueOf(Config.getCONFIG().speed * 1000));
        time.setText(String.valueOf(Config.getCONFIG().turnTime));
        port.setText(String.valueOf(Config.getCONFIG().port));
        ip.setText(Config.getCONFIG().ip);

    }

    public void change(){
        Config.getCONFIG().init(Integer.parseInt(speed.getText())/1000, Integer.parseInt(time.getText()),
                Integer.parseInt(port.getText()), ip.getText());
    }


    @FXML
    public void setBack() {
        change();
        GsonWriter.writeConfig();
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    @FXML
    public void setDefault(){
        Config.getCONFIG().init(1, 60, 8000, "127.0.0.1");
        init();
    }
}
