package view;

import Controller.Controller;
import Model.enums.ErrorType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SignIn extends Page {
    private View view = View.getInstance();
    public ImageView imageView;

    public SignIn(Stage stage) {
        super(stage);
        start();
        stage.show();
    }

    public void start() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXML/SignIn.fxml"));
            /*Image image = new Image(new FileInputStream("resources/main/codex/chapter17_background@2x.jpg"));
            imageView.setImage(image);*/
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void help() {
        view.showHelpForAccountMenu();
    }


    public void handleCommand(String command) {
        if (command.matches("create account .*"))
            controller.createAccount(command);
        else if (command.matches("login .*"))
            controller.loginAccount(command);
        else if (command.matches("show leaderboard"))
            controller.showLeaderBoard();
        else if (command.matches("exit"))
            view.exit();
        else if (command.matches("help"))
            view.showHelpForAccountMenu();
        else
            view.printError(ErrorType.INVALID_COMMAND);

    }

    @Override
    public void showMenu() {
        view.show("----------<Account>---------");
    }
}
