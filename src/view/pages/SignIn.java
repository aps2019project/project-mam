package view.pages;

import Controller.SigninController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SignIn extends Page {

    public SignIn(){
        start();
    }

    private SigninController controller;

    public void start() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/SignIn.fxml"));
            Pane  root = fxmlLoader.load();
            //root = FXMLLoader.load(getClass().getResource("../layout/SignIn.fxml"));
            controller = fxmlLoader.getController();
            setBackGround(root,"resources/codex/chapter17_background@2x.jpg", 500, 250);
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
