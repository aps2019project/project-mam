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

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeItem(Item item) {
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


    public String getCardInfo() {
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

    public String getHeroInfo() {
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

    public String getItemInfo() {
        StringBuilder info = new StringBuilder();
        int counter = 1;
        for (Item item : items) {
            info.append("\t\t").append(counter).append(" : ");
            info.append(item.getInfo()).append("\n");
            counter++;
        }
        return info.toString();
    }

    public String showCollection() {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Heroes :\n").append(getHeroInfo());
        allInfo.append("\nItems :\n").append(getItemInfo());
        allInfo.append("\nCards :\n").append(getCardInfo());
        return allInfo.toString();
    }

    public int getCardId(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return card.getId();
            }
        }
        return -1;
    }

    public int getItemId(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item.getId();
            }
        }
        return -1;
    }

    public Card getCard(int cardID) {
        for (Card card : cards) {
            if (card.getId() == cardID) {
                return card;
            }
        }
        return null;
    }

    public Item getItem(int itemID) {
        for (Item item : items) {
            if (item.getId() == itemID) {
                return item;
            }
        }
        return null;
    }

    public Deck getDeck(String deckName){
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)){
                return deck;
            }
        }
        return null;
    }

    public boolean searchItemInCollection(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchCardInCollection(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return true;
            }
        }
        return false;
    }

    public void createDeck(String deckName) {
        Deck deck = new Deck();
        deck.setName(deckName);
        decks.add(deck);
    }

    public boolean checkIsExistDeck(String name) {
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)) {
                decks.remove(deck);
                return;
            }
        }
    }

    public void addCardToDeck(Card card, String deckName){
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)){
                deck.addCard(card);
                return;
            }
        }
    }

    public void addItemToDeck(Item item, String deckName){

    }

    public void addHeroToDeck(Card hero, String deckName){

    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public void removeFromDeck(String command) {
    }

}
