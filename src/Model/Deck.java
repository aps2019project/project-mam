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

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void setItem(UsableItem item) {
        this.item = item;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public UsableItem getItem() {
        return item;
    }

    public Hero getHero() {
        return hero;
    }

    public boolean cardIsExist(int cardId){
        for (Card card : cards) {
            if (card.getId() == cardId){
                return true;
            }
        }
        return false;
    }

    public void removeCard(Card card){
        cards.remove(card);
    }
}
