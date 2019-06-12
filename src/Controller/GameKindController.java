package Controller;

import Model.enums.ErrorType;
import Model.game.Game;
import Model.user.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.BattleMenu.MissionsMenuPage;
import view.pages.Page;


public class GameKindController {

    public Label story;
    public Label custom;
    public Label label;

    public TextField deckName;

    public ImageView back;

    public void setStory() {
        BattleMenuPage.setGameKind("story");
        Page.getPages().push(new MissionsMenuPage());
    }

    public void setCustom() {
        if (isDeckValid(deckName.getText())) {
            BattleMenuPage.setGameKind("custom");
            BattleMenuPage.getSecondUser().setMainDeck(User.user.getCollection().getDeck(deckName.getText()));
            Page.getPages().push(new GameMoodMenuPage());
        } else label.setText(ErrorType.INVALID_DECK.getMessage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }



    public boolean isDeckValid(String deckName) {
        if (User.user.getCollection().isValidDeck(deckName)) {
            return true;
        }
        return false;
    }
}