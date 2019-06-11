package view.pages;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.View;
import view.pages.Page;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SignIn extends Page {
    private View view = View.getInstance();

    public SignIn(Stage stage) {
        super(stage);
        start();
        stage.show();
    }

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
