package Controller;

import Model.shop.Shop;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.pages.Page;

public class MissionController {

    public Label mission1;
    public Label mission2;
    public Label mission3;

    public ImageView back;

    public void setMission1() {
        BattleMenuPage.setMission("1");
        BattleMenuPage.setGameMood("1");
        BattleMenuPage.getSecondUser().setMainDeck(Shop.getDecks().get(0));
        BattleMenuPage.createGame();
        //Page.getPages().push(new GameMoodMenuPage());
    }

    public void setMission2() {
        BattleMenuPage.setMission("2");
        BattleMenuPage.setGameMood("2");
        BattleMenuPage.getSecondUser().setMainDeck(Shop.getDecks().get(1));
        BattleMenuPage.createGame();
        //Page.getPages().push(new GameMoodMenuPage());
    }

    public void setMission3() {
        BattleMenuPage.setMission("3");
        BattleMenuPage.setGameMood("3");
        BattleMenuPage.getSecondUser().setMainDeck(Shop.getDecks().get(2));
        BattleMenuPage.createGame();
        //Page.getPages().push(new GameMoodMenuPage());
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
