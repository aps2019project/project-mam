package Model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Spell> spells = new ArrayList<>();
    private UsableItem item;
    private Hero hero;

    public void setCards() {
        cards.add(spells.get(1));
    }
}
