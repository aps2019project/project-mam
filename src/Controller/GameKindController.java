package Controller;

import Model.enums.ErrorType;
import Model.game.Game;
import Model.gson.GsonReader;
import Model.user.User;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.BattleMenu.MissionsMenuPage;
import view.pages.Page;

import java.io.FileNotFoundException;


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
            BattleMenuPage.setGameKind("custom");                 //TODO : write real deck
            try {
                BattleMenuPage.getSecondUser().setMainDeck(GsonReader.getDeck("test2"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
