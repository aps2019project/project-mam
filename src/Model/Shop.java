package Model;

import java.util.ArrayList;

import static Model.TargetCommunity.*;
import static Model.BuffType.*;
import static Model.ImpactType.*;
import static Model.SPActivationTime.*;

public class Shop {
    private static final Shop SHOP = new Shop();

    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<UsableItem> items = new ArrayList<>();

    private Shop() {
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

    public String getCardInfo(String cardName) {
        StringBuilder info = new StringBuilder();
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                info.append(card.getInfo()).append("\n");
                return info.toString();
            }
        }
        return null;
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

    public String getItemInfo(String itemName) {
        StringBuilder info = new StringBuilder();
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                info.append(item.getInfo()).append("\n");
                return info.toString();
            }
        }
        return null;
    }

    public String show() {
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Heroes :\n").append(getHeroInfo());
        allInfo.append("\nItems :\n").append(getItemInfo());
        allInfo.append("\nCards :\n").append(getCardInfo());
        return allInfo.toString();
    }

    public boolean searchCard(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIsItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void buyCard(String cardName, User user) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                card.setId(user.getIdCounter());
                user.getCollection().addCard(card);
                user.setMoney(user.getMoney() - card.getPrice());
                user.setIdCounter(user.getIdCounter() + 1);
            }
        }
    }

    public void buyItem(String itemName, User user) {
        for (UsableItem item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                item.setId(user.getIdCounter());
                user.getCollection().addItem(item);
                user.setMoney(user.getMoney() - item.getPrice());
                user.setIdCounter(user.getIdCounter() + 1);
            }
        }
    }

    public int getCardPrice(String name) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(name))
                return card.getPrice();
        }
        return 0;
    }

    public int getItemPrice(String name) {
        for (UsableItem item : items) {
            if (item.getName().equalsIgnoreCase(name))
                return item.getPrice();
        }
        return 0;
    }

    public boolean isPossibleToAddItem(User user) {
        if (user.getCollection().getItems().size() < 3)
            return true;
        else
            return false;
    }

    public boolean cardNameIsAvailable(String cardName) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                return true;
            }
        }
        return false;
    }

    public boolean itemNameIsAvailable(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean priceIsEnough(int price, User user) {
        if (user.getMoney() >= price)
            return true;
        return false;
    }

    public boolean sellCard(int cardId, User user) {
        for (Card card : user.getCollection().getCards()) {
            if (card.getId() == cardId) {
                user.getCollection().removeCard(card);
                user.setMoney(user.getMoney() + card.getPrice());
                return true;
            }
        }
        return false;
    }

    public boolean sellItem(int itemId, User user) {
        for (Item item : user.getCollection().getItems()) {
            if (item.getId() == itemId) {
                user.getCollection().removeItem(item);
                user.setMoney(user.getMoney() + item.getPrice());
                return true;
            }
        }
        return false;
    }

    public static Shop getInstance() {
        return SHOP;
    }

    public static void importCards() {
        Card card = new Spell("Total Disarm", 1000, 0);
        card.addBuff(new Buff(DISARM, 100, 0, ONE_ENEMY_FORCE));
        cards.add(card);

        card = new Spell("Area Dispel", 2500, 2);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 0, 0, TWO_IN_TWO));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 0, 0, TWO_IN_TWO));
        cards.add(card);

        card = new Spell("Empower", 250, 1);
        card.addBuff(new Buff(ATTACK_POWER, 100, 2, ONE_INSIDER_FORCE));
        cards.add(card);

        card = new Spell("Fire Ball", 400, 1);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 0, 4, ONE_ENEMY_FORCE));
        cards.add(card);

        card = new Spell("God Strength", 450, 2);
        card.addBuff(new Buff(ATTACK_POWER, 100, 4, ONE_INSIDER_FORCE));
        cards.add(card);

        card = new Spell("Hell Fire", 600, 3);
        card.addBuff(new Buff(CELL_EFFECT_FIERY, 2, 2, TWO_IN_TWO));
        cards.add(card);

        card = new Spell("Lighting Bolt", 1250, 2);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 0, 8, ONE_ENEMY_FORCE));
        cards.add(card);

        card = new Spell("poison Lake", 900, 5);
        card.addBuff(new Buff(CELL_EFFECT_POSION, 1, 1, THREE_IN_THREE));
        cards.add(card);

        card = new Spell("Madness", 650, 0);
        card.addBuff(new Buff(ATTACK_POWER, 3, 4, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(DISARM, 3, 0, OWN));
        cards.add(card);

        card = new Spell("All Disarm", 2000, 9);
        card.addBuff(new Buff(DISARM, 1, 0, ALL_ENEMY_FORCES));
        cards.add(card);

        card = new Spell("All Poison", 1500, 8);
        card.addBuff(new Buff(POSION, 4, 0, ALL_ENEMY_FORCES));
        cards.add(card);

        card = new Spell("Dispel", 2100, 0);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 0, 0, ALL_INSIDER_FORCES));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 0, 0, ALL_ENEMY_FORCES));
        cards.add(card);

        card = new Spell("Health with profit", 2250, 0);
        card.addBuff(new Buff(HEALTH_WEAKNESS, 0, 1, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(HOLY, 3, 2, ONE_INSIDER_FORCE));
        cards.add(card);

        card = new Spell("Power Up", 2500, 2);
        card.addBuff(new Buff(ATTACK_POWER, 0, 6, ONE_INSIDER_FORCE));
        cards.add(card);




        //----------------------------minions-----------------------


        card = new Minion("kamandar fars", 300, 2, 6, 4, 7, RANGED, NULL);
        cards.add(card);

        card = new Minion("shamshirZan fars", 400, 2, 4, 6, 0, MELEE, ON_ATTACK);
        card.addBuff(new Buff(STUN, 1, 0, ONE_ENEMY_FORCE));
        cards.add(card);

        card = new Minion("neyzehDar fars", 500, 1, 5, 3, 0, HYBRID, NULL);
        cards.add(card);

        card = new Minion("asbSavar fars", 200, 4, 10, 6, 0, MELEE, NULL);
        cards.add(card);

        card = new Minion("pahlavan fars", 600, 9, 24, 6, 0, MELEE, ON_ATTACK);
        //???????????????????????
        cards.add(card);


        //-----------------------------heros-----------------------------------------

        card = new Hero("div sfid", 8000, 50, 4, MELEE, 0, 1, 2);
        card.addBuff(new Buff(ATTACK_POWER, 100, 4, OWN));
        cards.add(card);

        card = new Hero("simorgh", 9000, 50, 4, MELEE, 0, 5, 8);
        //card.addBuff(new Buff(""));
        //-----------------------------Item------------------------------------------
        UsableItem item = new UsableItem("tajdanaii", 300, "3time");
        items.add(item);

        item = new UsableItem("namosSpar", 4000, "12");
        items.add(item);

        item = new UsableItem("kamanDamol", 30000, " ");
        items.add(item);

        item = new UsableItem("noshDaroo", 0, "12");
        items.add(item);

        item = new UsableItem("tirDoshakh", 0, "12");
        items.add(item);

        item = new UsableItem("parSimorgh", 3500, "12");
        items.add(item);

        item = new UsableItem("eksir", 0, "12");
        items.add(item);

        item = new UsableItem("majonMana", 0, "12");
        items.add(item);

        item = new UsableItem("majonRoiintani", 0, "12");
        items.add(item);

        item = new UsableItem("nefrinMarg", 0, "12");
        items.add(item);

        item = new UsableItem("randomdamage", 0, "12");
        items.add(item);

        item = new UsableItem("terrorHood", 5000, "12");
        items.add(item);

        item = new UsableItem("bladesOfAgility", 0, "12");
        items.add(item);

        item = new UsableItem("kingWisdom", 9000, "12");
        items.add(item);

        item = new UsableItem("assassinationDagger", 15000, "12");
        items.add(item);

        item = new UsableItem("poisonousDagger", 7000, "12");
        items.add(item);

        item = new UsableItem("shockHammer", 15000, "12");
        items.add(item);

        item = new UsableItem("soulEater", 4000, "12");
        items.add(item);

        item = new UsableItem("ghoslTamid", 20000, "12");
        items.add(item);

        item = new UsableItem("shamshirChini", 0, "12");
        items.add(item);
    }

}


















