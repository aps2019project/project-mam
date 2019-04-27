package Model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private UsableItem item;
    private Card hero;
    private String name;

    public void setName(String name) {
        this.name = name;
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
        info.append("\t\t");
        if (hero != null) {
            info.append(hero.getInfo());
            return info.toString();
        }
        return "";
    }

    public String getItemInfo() {
        StringBuilder info = new StringBuilder();
        info.append("\t\t");
        if (item != null) {
            info.append(item.getInfo());
            return info.toString();
        }
        return "";
    }

    public String showDeck() {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Hero :\n");
        if (getHeroInfo() != null) {
            allInfo.append(getHeroInfo());
        }
        allInfo.append("\nItem :\n");
        if (getItemInfo() != null) {
            allInfo.append(getItemInfo());
        }
        allInfo.append("\nCards :\n");
        if (getCardInfo() != null) {
            allInfo.append(getCardInfo());
        }
        return allInfo.toString();
    }

    public String showDecks() {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("\t\tHero :\n");
        if (getHeroInfo() != null) {
            allInfo.append("\t\t").append(getHeroInfo());
        }
        allInfo.append("\n\t\tItem :\n");
        if (getItemInfo() != null) {
            allInfo.append("\t\t").append(getItemInfo());
        }
        allInfo.append("\n\t\tCards :\n");
        if (getCardInfo() != null) {
            allInfo.append(getCardInfo());
        }
        return allInfo.toString();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {this.cards.remove(card); }

    public void addItem(UsableItem item) {
        this.item = item;
    }

    public void removeItem(UsableItem item) {
        this.item = item;
    }

    public void setHero(Card hero) {
        this.hero = hero;
    }

    public void removeHero(Hero hero) {
        this.hero = null;
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

    public Card getHero() {
        return hero;
    }

    public boolean cardIsExist(int cardId) {
        for (Card card : cards) {
            if (card.getId() == cardId) {
                return true;
            }
        }
        return false;
    }
}
