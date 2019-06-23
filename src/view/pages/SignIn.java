package view.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn extends Page {

    public SignIn(){
        start();
    }

    public void start() {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("../layout/SignIn.fxml"));
            setBackGround(root,"resources/codex/chapter17_background@2x.jpg", 500, 250);
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
