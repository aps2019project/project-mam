package Model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private UsableItem item;
    private Hero hero;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
