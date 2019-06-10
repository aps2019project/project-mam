package view.pages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUp extends Page{
    public SignUp(Stage stage) {
        super(stage);
    }

    public SignUp(){
        start();
    }

    public void start(){
        try {
            Pane root = FXMLLoader.load(getClass().getResource("../layout/SignUp.fxml"));
            setBackGround(root,"resources/codex/chapter17_background@2x.jpg", 500, 250);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
