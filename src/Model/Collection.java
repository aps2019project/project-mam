package Model;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck;
    public ArrayList<Deck> getDecks() {
        return decks;
    }
    public void addCard(Card card){
        cards.add(card);
    }
    public void addItem(Item item){
        items.add(item);
    }
    public Deck getMainDeck() {
        return mainDeck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
