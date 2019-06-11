package Controller;

import Model.enums.ErrorType;
import Model.user.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.pages.Page;

public class SelectUserController {

    public Label users;
    public Label select;
    public Label label;

    public TextField user;

    public ImageView back;

    public void setSelect() {
        if (!User.isUserNameNew(user.getText())) {
            BattleMenuPage.getInstance().setSecondUser(User.getUser(user.getText()));
            Page.getPages().push(new GameMoodMenuPage());
        } label.setText(ErrorType.INVALID_USERNAME.getMessage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void showUsers() {
        StringBuilder info = new StringBuilder();
        info.append("Accounts:\n");
        for (User users : User.getUsers()) {
            if (!users.getName().equalsIgnoreCase(User.user.getName())) {
                info.append("\t").append("Name: ").append(users.getName()).append("    Num of Wins: ").append(users.getNumberOfWin()).append("\n");
            }
        }
        users.setText(info.toString());
    }
}
