package Controller;


import Model.enums.ErrorType;
import Model.user.User;
import command.Result;
import command.ServerCommand;
import command.clientCommand.SignUpCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.pages.MainMenuPage;
import view.pages.Page;

public class SignUpController {
    public TextField userName;
    public PasswordField password;
    public Button btn;
    public Label lb;

    public void onBtnClicked() {
        create(userName.getText(), password.getText());
    }

    private void create(String userName, String password){
        GsonWriter.sendClientCommand(new SignUpCmd(userName, password), Page.getOutput());
        ServerCommand serverCommand = GsonReader.getServerCommand(Page.getInput());
        if (serverCommand.getResult() == Result.SUCCESSFUL) {
            User.addUser(new User(userName, password));
            Page.getPages().pop();
            Page.getPages().peek().start();
        } else lb.setText(serverCommand.getMessage());
    }

}
