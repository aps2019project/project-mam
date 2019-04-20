package Model;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<>();
    private  ArrayList<UsableItem>  items=new ArrayList<>();
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
        int counter = 1;
        if(hero!=null) {
            info.append("\t\t").append(counter).append(" : ");
            info.append(hero.getInfo()).append("\n");
            counter++;
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

    public String showDeck() {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Heroes :\n").append(getHeroInfo());
        allInfo.append("\nItems :\n").append(getItemInfo());
        allInfo.append("\nCards :\n").append(getCardInfo());
        return allInfo.toString();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeCard(Card card) {this.cards.remove(card); }

    public void addItem(UsableItem item) {
        this.items.add(item);
    }

    public void removeItem(UsableItem item) { this.items.remove(item); }

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

    public ArrayList getItem() {
        return items;
    }

    public Card getHero() {
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

}
