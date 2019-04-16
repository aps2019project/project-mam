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


    public void removeFromDeck(String command) {
    }

    public String getCardInfo(){
        StringBuilder info = new StringBuilder();
        int counter = 1;
        for (Card card : cards) {
            if (card.getCardType().equals("minion") || card.getCardType().equals("spell")) {
                info.append("\t\t").append(counter).append(" : ");
                info.append(card.getInfo()).append("\n");
                counter++;
            }
        }
        return info.toString();
    }
    public String getHeroInfo(){
        StringBuilder info = new StringBuilder();
        int counter = 1;
        for (Card card : cards) {
            if (card.getCardType().equals("hero")) {
                info.append("\t\t").append(counter).append(" : ");
                info.append(card.getInfo()).append("\n");
                counter++;
            }
        }
        return info.toString();
    }

    public String getItemInfo(){
        StringBuilder info = new StringBuilder();
        int counter = 1;
        for (Item item : items) {
            info.append("\t\t").append(counter).append(" : ");
            info.append(item.getInfo()).append("\n");
            counter++;
        }
        return info.toString();
    }

    public String showCollection(User user){
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Heroes :\n").append(user.getCollection().getHeroInfo());
        allInfo.append("\nItems :\n").append(user.getCollection().getItemInfo());
        allInfo.append("\nCards :\n").append(user.getCollection().getCardInfo());
        return allInfo.toString();
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
