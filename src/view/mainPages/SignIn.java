package view.mainPages;

import Controller.FXML.SignInController;
import Model.enums.ErrorType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.View;

import java.io.IOException;

public class SignIn extends Page {
    private View view = View.getInstance();

    public SignIn(Stage stage) {
        super(stage);
        start();
        stage.show();
    }

    public void start() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/layout/SignIn.fxml"));
            new SignInController().init();
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
