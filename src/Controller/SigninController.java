package Controller;

import Model.enums.ErrorType;
import Model.user.User;
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
    public void onSignInBtnClicked(){
        loginAccount(userName.getText(), password.getText());
    }

    @FXML
    public void onSignUpClicked(){
        Page.getPages().push(new SignUp());

    }


    public void loginAccount(String userName, String password) {
        if (!User.isUserNameNew(userName)) {
            if (User.isPassCorrect(userName, password)) {
                User.user = User.login(userName, password);
                Page.getPages().push(new MainMenuPage());
            } else msgLb.setText(ErrorType.INCORRECT_PASSWORD.getMessage());
        } else msgLb.setText(ErrorType.INVALID_USERNAME.getMessage());
    }
}
