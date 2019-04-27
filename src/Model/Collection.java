package Model;

import java.util.ArrayList;

public class Collection {
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<UsableItem> items = new ArrayList<>();
    private ArrayList<Deck> decks = new ArrayList<>();
    private Deck mainDeck;

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
            allInfo.append(counter).append(" : deck_").append(counter).append(" : \n");
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
            if (deck.getName().equalsIgnoreCase(deckName)) {
                return deck;
            }
        }
        return null;
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

    public void removeCardFromDeck(Card card, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.removeCard(card);
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

    public void removeItemFromDeck(UsableItem item, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.removeItem(item);
                return;
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

    public void removeHeroToDeck(Hero hero, String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                deck.removeHero(hero);
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

    public boolean validateDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                if (deck.getCards().size() == 20 && deck.getHero() != null)
                    return true;
            }
        }
        return false;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public boolean isValidMainDeck(String deckName) {
        for (Deck deck : decks) {
            if (deck.getName().equals(deckName)) {
                return true;
            }
        }
        return false;
    }

    public void removeFromDeck(String command) {
    }

}
