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
    public void removeCard(Card card){
        cards.remove(card);
    }
    public void removeItem(Item item){
        items.remove(item);
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

    public boolean searchItemInCollection(String itemName,User user){
        for (Item item: user.getCollection().getItems()) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public void creatDecks(String deckName){
        for (Deck deck : decks) {
            if(!deck.getName().equals(deckName)){
                deck.setName(deckName);

            }
        }
    }
    public boolean checkIsexistDeck(String name){
        for (Deck deck : decks) {
            if(deck.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void deleteDecks(String deckName){
        for (Deck deck : decks) {
            if(deck.getName().equals(deckName)){
                decks.remove(deck);
            }
        }
    }

    public void addCard(String name){
        

    }
}
