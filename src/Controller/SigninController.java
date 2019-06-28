package Controller;

import Model.enums.ErrorType;
import Model.user.User;
import command.ClientCommand;
import command.CommandType;
import command.Result;
import command.ServerCommand;
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

    private void login(String userName, String password){
        ClientCommand command  = new ClientCommand();
        command.setType(CommandType.SIGNIN);
        command.setUserName(userName);
        command.setPass(password);
        GsonWriter.sendClientCommand(command, Page.getOutput());
        ServerCommand serverCommand = GsonReader.getServerCommand(Page.getInput());
        if (serverCommand.getResult() == Result.SUCCESSFUL)
            Page.getPages().push(new MainMenuPage());
        else msgLb.setText(serverCommand.getMessage());
    }

}
