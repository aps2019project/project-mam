package Controller;

import Model.shop.Shop;
import gson.GsonReader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.pages.Page;

import java.io.FileNotFoundException;

public class MissionController {

    public Label mission1;
    public Label mission2;
    public Label mission3;

    public ImageView back;

    public void setMission1() {
        BattleMenuPage.setMission("1");
        BattleMenuPage.setGameMood("1");
        try {
            BattleMenuPage.getSecondUser().setMainDeck(GsonReader.getDeck("mission_1"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BattleMenuPage.createGame();
    }

    public void setMission2() {
        BattleMenuPage.setMission("2");
        BattleMenuPage.setGameMood("2");
        BattleMenuPage.getSecondUser().setMainDeck(Shop.getDecks().get(1));
        BattleMenuPage.setFlags(1);
        BattleMenuPage.createGame();
    }

    public void setMission3() {
        BattleMenuPage.setMission("3");
        BattleMenuPage.setGameMood("3");
        BattleMenuPage.getSecondUser().setMainDeck(Shop.getDecks().get(2));
        BattleMenuPage.setFlags(7);
        BattleMenuPage.createGame();
    }

    public void setBack() {
        Page.getPages().pop();
        Page.getPages().peek().start();
    }
}
