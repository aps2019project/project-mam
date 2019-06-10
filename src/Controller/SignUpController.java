package Controller;


import Model.enums.ErrorType;
import Model.user.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import view.mainPages.Page;

public class SignUpController {
    public TextField userName;
    public PasswordField password;
    public Button btn;
    public Label lb;

    public void onBtnClicked(){
        createAccount(userName.getText(), password.getText());
    }

    public void createAccount(String userName, String password) {
        if (User.isUserNameNew(userName)) {
            User.addUser(new User(userName, password));
            Page.getPages().pop();
            Page.getPages().peek().start();
        }
        else lb.setText(ErrorType.DUPLICATE_USERNAME.getMessage());
    }

}
