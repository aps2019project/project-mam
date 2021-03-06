package Model.collection;

import Model.deck.Deck;
import Model.card.Card;
import Model.card.Hero;
import Model.item.UsableItem;
import Model.user.User;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<UsableItem> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck = new Deck();

    public ArrayList<Deck> getDecks() {
        return decks;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addItem(UsableItem item) {
        items.add(item);
    }

    public void removeCard(Card card) {
        cards.remove(card);
    }

    public void removeItem(UsableItem item) {
        items.remove(item);
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<UsableItem> getItems() {
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
        for (UsableItem item : items) {
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

    public String showDeck(String deckName){
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)){
                return deck.showDeck();
            }
        }
        return null;
    }

    public String showAllDecks() {
        StringBuilder allInfo = new StringBuilder();
        int counter = 1;
        for (Deck deck : decks) {
            allInfo.append(counter).append(" : ").append(deck.getName()).append(" : \n");
            allInfo.append(deck.showDecks());
            counter++;
        }
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
        for (UsableItem item : items) {
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

    public UsableItem getItem(int itemID) {
        for (UsableItem item : items) {
            if (item.getId() == itemID) {
                return item;
            }
        }
        return null;
    }

    public Deck getDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                return deck;
            }
        }
        return null;
    }

    public boolean isDeckExist(String deckName){
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCardsInDeckExist(Deck deck){
        if (deck.getCards().size() == 0)
            return true;
        for (Card card : deck.getCards()) {
            if (!searchCardInCollection(card.getName())){
                return false;
            }
        }
        return searchItemInCollection(deck.getItem().getName());
    }

    public boolean searchItemInCollection(String itemName) {
        for (UsableItem item : items) {
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
            if (deck.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                decks.remove(deck);
                return;
            }
        }
    }

    public void addCardToDeck(Card card, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.addCard(card);
                return;
            }
        }
    }



    public void addItemToDeck(UsableItem item, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.addItem(item);
                return;
            }
        }
    }

    public void removeCardFromDeck(Card card, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.removeCard(card);
                return;
            }
        }
    }

    public void removeFromDeck(int id, String deckName){
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)){
                for (Card card : deck.getCards()) {
                    if (card.getId() == id){
                        deck.removeCard(card);
                        return;
                    }
                }
                if (deck.getHero().getId() == id){
                    deck.removeHero();
                    return;
                }
                if (deck.getItem().getId() == id){
                    deck.removeItem();
                    return;
                }
            }
        }
    }

    public void addHeroToDeck(Card hero, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)) {
                deck.setHero(hero);
                return;
            }
        }
    }



    public boolean cardIsExistInCollection(int cardId, User user) {
        for (Card card : user.getCollection().getCards()) {
            if (card.getId() == cardId) {
                return true;
            }
        }
        return false;
    }

    public boolean itemIsExistInCollection(int itemId, User user) {
        for (UsableItem item : user.getCollection().getItems()) {
            if (item.getId() == itemId) {
                return true;
            }
        }
        return false;
    }

    public boolean heroIsExistInDeck(int heroId, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                if (deck.getHero() == null)
                    return true;
            }
        }
        return false;
    }

    public boolean isValidDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                if (deck.getCards().size() == 20 && deck.getHero() != null)
                    return true;
            }
        }
        return false;
    }

    public void setMainDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equalsIgnoreCase(deckName)){
                this.mainDeck = deck;
            }
        }
    }

    public void setMainDeck(Deck deck){
        this.mainDeck = deck;
    }

    public boolean isValidMainDeck() {
        return isValidDeck(mainDeck.getName());
    }

    public void removeFromDeck(String command) {
    }

}
