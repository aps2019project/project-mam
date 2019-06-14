package Controller;

import Model.enums.ErrorType;
import Model.user.User;
import javafx.fxml.FXML;
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

    @FXML
    public void setSelect() {
        if (!User.isUserNameNew(user.getText())) {
            if (isMainDeckValid(user.getText())) {
                BattleMenuPage.setSecondUser(User.getUser(user.getText()));
                Page.getPages().push(new GameMoodMenuPage());
            } else label.setText(ErrorType.INVALID_DECK_2.getMessage());
        }
        label.setText(ErrorType.INVALID_USERNAME.getMessage());
    }

    @FXML
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

    public boolean isMainDeckValid(String userName) {
        try {
            return User.getUser(userName).getCollection().isValidMainDeck();
        } catch (NullPointerException e) {
            return false;
        }
    }
}
