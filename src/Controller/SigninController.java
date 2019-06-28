package Controller;

import Model.user.User;
import command.Result;
import command.ServerCommand;
import command.clientCommand.SignInCmd;
import command.clientCommand.SignInCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.pages.MainMenuPage;
import view.pages.Page;
import view.pages.SignUp;

public class SigninController {
    public Button signIn;
    public Label msgLb;
    public TextField userName;
    public PasswordField password;


    @FXML
    public void onSignInBtnClicked() {
        login(userName.getText(), password.getText());
    }

    @FXML
    public void onSignUpClicked() {
        Page.getPages().push(new SignUp());
    }

    private void login(String userName, String password) {
        GsonWriter.sendClientCommand(new SignInCmd(userName, password), Page.getOutput());
        ServerCommand serverCommand = GsonReader.getServerCommand(Page.getInput());
        if (serverCommand.getResult() == Result.SUCCESSFUL) {
            User.user = serverCommand.getUser();
            Page.getPages().push(new MainMenuPage());
        } else msgLb.setText(serverCommand.getMessage());
    }

}
