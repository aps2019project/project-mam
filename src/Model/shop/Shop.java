package Model.shop;

import Model.Buffs.Buff;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import Model.deck.Deck;
import Model.enums.TargetCommunity;
import Model.item.CollectableItem;
import Model.item.UsableItem;
import Model.user.User;

import java.util.ArrayList;

import static Model.enums.TargetCommunity.*;
import static Model.enums.BuffType.*;
import static Model.enums.ImpactType.*;
import static Model.enums.SPActivationTime.*;

public class Shop {
    private static final Shop SHOP = new Shop();
    private static ArrayList<Deck> decks=new ArrayList<>();
    private static ArrayList<Card> spells = new ArrayList<>();
    private static ArrayList<Card> cards = new ArrayList<>();
    private static ArrayList<Card> heros = new ArrayList<>();
    private static ArrayList<Card> minions = new ArrayList<>();
    private static ArrayList<UsableItem> items = new ArrayList<>();
    private static ArrayList<CollectableItem> collectibles = new ArrayList<>();

    private Shop() {
    }

    public static ArrayList<Deck> getDecks() {
        return decks;
    }

    public static ArrayList<CollectableItem> getCollectibles() {
        return collectibles;
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
        for (UsableItem item : items) {
            info.append("\t\t").append(counter).append(" : ");
            info.append(item.getInfo()).append("\n");
            counter++;
        }
        return info.toString();
    }

    public String getItemInfo(String itemName) {
        StringBuilder info = new StringBuilder();
        for (UsableItem item : items) {
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
        for (UsableItem item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIsItem(String name) {
        for (UsableItem item : items) {
            if (item.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void buyCard(String cardName, User user) {
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName)) {
                Card newCard = card.copyCard();
                newCard.setId(user.getIdCounter());
                user.getCollection().addCard(newCard);
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
        for (UsableItem item : items) {
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
        for (UsableItem item : user.getCollection().getItems()) {
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
        Card card = new Spell("TotalDisarm", 1000, 0);
        card.addBuff(new Buff(DISARM, -1, 0, ONE_ENEMY_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("AreaDispel", 1500, 2);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 1, 0, TWO_IN_TWO));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 1, 0, TWO_IN_TWO));
        spells.add(card);
        cards.add(card);

        card = new Spell("Empower", 250, 1);
        card.addBuff(new Buff(ATTACK_POWER, -1, 2, ONE_INSIDER_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("FireBall", 400, 1);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 4, ONE_ENEMY_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("GodStrength", 450, 2);
        card.addBuff(new Buff(ATTACK_POWER, -1, 4, INSIDER_HERO));
        spells.add(card);
        cards.add(card);

        card = new Spell("HellFire", 600, 3);
        card.addBuff(new Buff(CELL_EFFECT_FIERY, 2, 0, TWO_IN_TWO));
        spells.add(card);
        cards.add(card);

        card = new Spell("LightingBolt", 1250, 2);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 8, ENEMY_HERO));
        spells.add(card);
        cards.add(card);

        card = new Spell("poisonLake", 900, 5);
        card.addBuff(new Buff(CELL_EFFECT_POISON, 1, 0, THREE_IN_THREE));
        spells.add(card);
        cards.add(card);

        card = new Spell("Madness", 650, 0);
        card.addBuff(new Buff(ATTACK_POWER, 3, 4, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(DISARM, 3, 0, ONE_INSIDER_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("AllDisarm", 2000, 9);
        card.addBuff(new Buff(DISARM, 1, 0, ALL_ENEMY_FORCES));
        spells.add(card);
        cards.add(card);

        card = new Spell("AllPoison", 1500, 8);
        card.addBuff(new Buff(POISON, 4, 0, ALL_ENEMY_FORCES));
        spells.add(card);
        cards.add(card);

        card = new Spell("Dispel", 2100, 0);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 1, 0, ALL_INSIDER_FORCES));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 1, 0, ALL_ENEMY_FORCES));
        spells.add(card);
        cards.add(card);

        card = new Spell("HealthWithProfit", 2250, 0);
        card.addBuff(new Buff(HEALTH_WEAKNESS, 1, 6, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(HOLY, 3, 2, ONE_INSIDER_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("PowerUp", 2500, 2);
        card.addBuff(new Buff(ATTACK_POWER, -1, 6, ONE_INSIDER_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("AllPower", 2000, 4);
        card.addBuff(new Buff(ATTACK_POWER, -1, 2, ALL_INSIDER_FORCES));
        spells.add(card);
        cards.add(card);

        card = new Spell("AllAttack", 1500, 4);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 6, ALL_ENEMY_FORCES_IN_COLUMN));
        spells.add(card);
        cards.add(card);

        card = new Spell("Weakening", 1000, 1);
        card.addBuff(new Buff(ATTACK_WEAKNESS, 1, 4, ONE_ENEMY_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("Sacrifice", 1600, 2);
        card.addBuff(new Buff(HEALTH_WEAKNESS, 1, 6, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(ATTACK_POWER, 1, 8, ONE_INSIDER_FORCE));
        spells.add(card);
        cards.add(card);

        card = new Spell("KingsGuard", 1750, 9);
        card.addBuff(new Buff(KILL, 1, 0, ONE_ENEMY_FORCE_RANDOM));
        spells.add(card);
        cards.add(card);

        card = new Spell("Shock", 1200, 1);
        card.addBuff(new Buff(STUN, 2, 0, ONE_ENEMY_FORCE));
        spells.add(card);
        cards.add(card);

        //----------------------------minions-----------------------


        card = new Minion("kamandarFars", 300, 2, 6, 4, 7, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("shamshirzanFars", 400, 2, 6, 4, 0, MELEE, ON_ATTACK);
        card.addBuff(new Buff(STUN, 1, 0, ONE_ENEMY_FORCE));//???????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("neyzehdarFars", 500, 1, 5, 3, 3, HYBRID, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("asbSavarFars", 200, 4, 10, 6, 0, MELEE, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("pahlavanFars", 600, 9, 24, 6, 0, MELEE, ON_ATTACK);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 5 + card.getAP(), ONE_ENEMY_FORCE));//????????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("sepahSalarFars", 800, 7, 12, 4, 0, MELEE, COMBO);
        cards.add(card);
        minions.add(card);

        card = new Minion("kamandarTorani", 500, 1, 3, 4, 5, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("gholabSangdarTorani", 600, 1, 4, 2, 7, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("neyzedaranTorani", 600, 1, 4, 4, 3, HYBRID, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("jasosanTorani", 700, 4, 6, 6, 0, MELEE, NULL);
        card.addBuff(new Buff(DISARM, 1, 0, ONE_ENEMY_FORCE));
        card.addBuff(new Buff(POISON, 4, 0, ONE_ENEMY_FORCE));//???????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("gorazdarTorani", 450, 2, 3, 10, 0, MELEE, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("shahzadeTorani", 800, 6, 6, 10, 0, MELEE, COMBO);
        cards.add(card);
        minions.add(card);

        card = new Minion("DiveSiah", 300, 9, 14, 10, 7, HYBRID, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("gholSangAndaz", 300, 9, 12, 12, 7, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("oghab", 200, 2, 0, 2, 3, RANGED, PASSIVE);
        card.addBuff(new Buff(HEALTH_POWER, -1, 10, OWN));
        cards.add(card);
        minions.add(card);

        card = new Minion("DiveGorazSavar", 300, 6, 16, 8, 0, MELEE, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("gholTakCheshm", 500, 7, 12, 11, 3, HYBRID, ON_DEATH);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 2, ALL_ENEMY_FORCES_CLOSE));
        cards.add(card);
        minions.add(card);

        card = new Minion("marSami", 300, 4, 5, 6, 4, RANGED, ON_ATTACK);
        card.addBuff(new Buff(POISON, 3, 0, ONE_ENEMY_FORCE));
        cards.add(card);
        minions.add(card);

        card = new Minion("ezhdehayeAtashAndaz", 250, 5, 9, 5, 4, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("shirDarande", 600, 2, 1, 8, 0, MELEE, ON_ATTACK);
        //card.addBuff(new Buff());??????????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("marGholPeykar", 500, 8, 14, 7, 5, RANGED, ON_SPAWN);
        //??????????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("gorgSefid", 400, 5, 8, 2, 0, MELEE, ON_ATTACK);
        Buff buff = new Buff(HEALTH_POWER, 2, -6, ONE_ENEMY_FORCE);
        buff.setBuffPower(-8 + 4 / buff.getTime());
        buff.setUsed(true);
        buff.setStarted(true);
        card.addBuff(buff);
        cards.add(card);
        minions.add(card);

        card = new Minion("palang", 400, 4, 6, 2, 0, MELEE, ON_ATTACK);
        buff = new Buff(HEALTH_POWER, 1, -8, ONE_ENEMY_FORCE);
        card.addBuff(buff);
        cards.add(card);
        minions.add(card);

        card = new Minion("gorg", 400, 3, 6, 1, 0, MELEE, ON_ATTACK);
        buff = new Buff(HEALTH_POWER, 1, -6, ONE_ENEMY_FORCE);
        buff.setStarted(true);
        buff.setUsed(true);
        card.addBuff(buff);
        cards.add(card);
        minions.add(card);

        card = new Minion("jadogar", 550, 4, 5, 4, 3, RANGED, PASSIVE);
        card.addBuff(new Buff(ATTACK_POWER, 1, 2, OWN_AND_INSIDER_FORCES_CLOSE));
        card.addBuff(new Buff(HEALTH_WEAKNESS, 1, 1, OWN_AND_INSIDER_FORCES_CLOSE));
        cards.add(card);
        minions.add(card);

        card = new Minion("jadogarAzam", 550, 6, 6, 6, 5, RANGED, PASSIVE);
        card.addBuff(new Buff(ATTACK_POWER, 2, -1, true, OWN_AND_INSIDER_FORCES_CLOSE));
        card.addBuff(new Buff(HOLY, 0, -1, true, OWN_AND_INSIDER_FORCES_CLOSE));
        cards.add(card);
        minions.add(card);

        card = new Minion("jen", 500, 5, 10, 4, 4, RANGED, ON_INSERT);//?????????
        card.addBuff(new Buff(ATTACK_POWER, 1, -1, true, ALL_INSIDER_FORCES));
        cards.add(card);
        minions.add(card);

        card = new Minion("gorazVahshi", 500, 6, 10, 14, 0, MELEE, ON_DEFEND);
        cards.add(card);//????????????
        minions.add(card);

        card = new Minion("piran", 400, 8, 20, 12, 0, MELEE, ON_DEFEND);
        cards.add(card);//???????????
        minions.add(card);

        card = new Minion("give", 450, 4, 5, 7, 5, RANGED, ON_DEFEND);
        cards.add(card);//???????????
        minions.add(card);

        card = new Minion("bahman", 450, 8, 16, 9, 0, MELEE, ON_SPAWN);
        card.addBuff(new Buff(HEALTH_POWER, -1, -16, ONE_ENEMY_FORCE_RANDOM));
        cards.add(card);
        minions.add(card);

        card = new Minion("ashkbos", 400, 7, 14, 8, 0, MELEE, ON_DEFEND);
        cards.add(card);//???????????
        minions.add(card);

        card = new Minion("iraj", 500, 4, 6, 20, 3, RANGED, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("gholBozorg", 600, 9, 30, 8, 2, HYBRID, NULL);
        cards.add(card);
        minions.add(card);

        card = new Minion("gholTwoSar", 550, 4, 10, 4, 0, MELEE, ON_ATTACK);
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 1, 0, ONE_ENEMY_FORCE));//?????????????????
        cards.add(card);
        minions.add(card);

        card = new Minion("naneSarma", 500, 3, 3, 4, 5, RANGED, ON_SPAWN);
        card.addBuff(new Buff(STUN, 1, 0, ALL_ENEMY_FORCES_CLOSE));
        cards.add(card);
        minions.add(card);

        card = new Minion("foladZereh", 650, 3, 1, 1, 0, MELEE, PASSIVE);
        card.addBuff(new Buff(HOLY, 12, -1, true, OWN));
        cards.add(card);
        minions.add(card);

        card = new Minion("siavosh", 350, 4, 8, 5, 0, MELEE, ON_DEATH);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 6, ENEMY_HERO));
        cards.add(card);
        minions.add(card);

        card = new Minion("shahGhol", 600, 5, 10, 4, 0, MELEE, COMBO);
        cards.add(card);
        minions.add(card);

        card = new Minion("arzhangDiv", 600, 3, 6, 6, 0, MELEE, COMBO);
        cards.add(card);
        minions.add(card);
        //-----------------------------heroes-----------------------------------------

        card = new Hero("divsfid", 8000, 50, 4, MELEE, 0, 1, 2);
        card.addBuff(new Buff(ATTACK_POWER, -1, 4, OWN));
        cards.add(card);
        heros.add(card);

        card = new Hero("simorgh", 9000, 50, 4, MELEE, 0, 5, 8);
        card.addBuff(new Buff(STUN, 1, 0, ALL_ENEMY_FORCES));
        cards.add(card);
        heros.add(card);

        card = new Hero("ezhdehayeHaftsar", 8000, 50, 4, MELEE, 0, 0, 1);
        card.addBuff(new Buff(DISARM, 1, 0, ONE_ENEMY_FORCE));
        cards.add(card);
        heros.add(card);

        card = new Hero("rakhsh", 8000, 50, 4, MELEE, 0, 1, 2);
        card.addBuff(new Buff(STUN, 1, 0, ONE_ENEMY_FORCE));
        cards.add(card);
        heros.add(card);

        card = new Hero("zahak", 10000, 50, 2, MELEE, 0, 0, 0);
        card.addBuff(new Buff(POISON, 3, 0, ONE_ENEMY_FORCE));//?????????????????????
        cards.add(card);
        heros.add(card);

        card = new Hero("kaveh", 8000, 50, 4, MELEE, 0, 1, 3);
        card.addBuff(new Buff(CELL_EFFECT_HOLY, 3, 0, ONE_CELL));
        cards.add(card);
        heros.add(card);

        card = new Hero("arash", 10000, 30, 2, RANGED, 6, 2, 2);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 1, 4, ALL_ENEMY_FORCES_IN_ROW));
        cards.add(card);
        heros.add(card);

        card = new Hero("afsane", 11000, 40, 3, RANGED, 3, 1, 2);
        card.addBuff(new Buff(DISPEL, 1, 0, ONE_ENEMY_FORCE));
        cards.add(card);
        heros.add(card);

        card = new Hero("esfandyar", 12000, 35, 3, HYBRID, 3, 0, 0);
        card.addBuff(new Buff(HOLY, -1, 3,true, OWN));
        cards.add(card);
        heros.add(card);

        card = new Hero("rostam", 8000, 55, 7, HYBRID, 4, 0, 0);
        cards.add(card);
        heros.add(card);

        //-----------------------------Item------------------------------------------
        UsableItem item = new UsableItem("tajdanaii", 300, "3time");
        item.addBuff(new Buff(INCREASE_MANA, 3, 1, TargetCommunity.NONE));
        items.add(item);

        item = new UsableItem("namosSpar", 4000, "12");
        item.addBuff(new Buff(HOLY, -1, 12, INSIDER_HERO));
        items.add(item);

        item = new UsableItem("kamanDamol", 30000, " ");
        item.addBuff(new Buff(DISARM, 1, 0, INSIDER_HERO));//???????????????
        items.add(item);

        item = new UsableItem("parSimorgh", 3500, "12");
        item.addBuff(new Buff(ATTACK_POWER, -1, -2, ENEMY_HERO));//???????????????????
        items.add(item);

        item = new UsableItem("terrorHood", 5000, "12");
        item.addBuff(new Buff(ATTACK_WEAKNESS, 1, 2, ONE_ENEMY_FORCE_RANDOM));
        items.add(item);

        item = new UsableItem("kingWisdom", 9000, "12");
        item.addBuff(new Buff(INCREASE_MANA, -1, 1, TargetCommunity.NONE));
        items.add(item);

        item = new UsableItem("assassinationDagger", 15000, "12");
        item.addBuff(new Buff(ATTACK_INSERT, -1, 1, ENEMY_HERO));//???????????????
        items.add(item);

        item = new UsableItem("poisonousDagger", 7000, "12");
        //item.addBuff(new Buff());  ??????????????????????
        items.add(item);

        item = new UsableItem("shockHammer", 15000, "12");
        //???????????????????????
        items.add(item);

        item = new UsableItem("soulEater", 4000, "12");
        //?????????????????????
        items.add(item);

        item = new UsableItem("ghoslTamid", 20000, "12");
        //???????????????
        items.add(item);

        //-----------------------------collectible--------------------------------------

        CollectableItem collectableItem = new CollectableItem("noshDaroo", "12");
        collectableItem.addBuff(new Buff(HEALTH_POWER, -1, 6, ONE_INSIDER_FORCE_RANDOM));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("tirDoshakh","12");
        collectableItem.addBuff(new Buff(ATTACK_POWER, -1, 2, RANGED_HYBRID));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("eksir","12");
        collectableItem.addBuff(new Buff(HEALTH_POWER, -1, 3, ONE_INSIDER_FORCE_RANDOM));
        collectableItem.addBuff(new Buff(ATTACK_POWER, -1, 3, ONE_INSIDER_FORCE_RANDOM));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("majonMana","12");
        collectableItem.addBuff(new Buff(INCREASE_MANA, 1, 3, TargetCommunity.NONE));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("majonRoiintani","12");
        collectableItem.addBuff(new Buff(HOLY, 2, 10, ONE_INSIDER_FORCE_RANDOM));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("nefrinMarg","12");
        collectableItem.addBuff(new Buff(POISON, -1, 8, ONE_ENEMY_FORCE_RANDOM));//?
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("randomDamage","12");
        collectableItem.addBuff(new Buff(ATTACK_POWER, -1, 2, ONE_INSIDER_FORCE_RANDOM));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("bladesOfAgility","12");
        collectableItem.addBuff(new Buff(ATTACK_POWER, -1, 6, ONE_INSIDER_FORCE_RANDOM));
        collectibles.add(collectableItem);

        collectableItem = new CollectableItem("shamshirChini","12");
        collectableItem.addBuff(new Buff(ATTACK_POWER, -1, 5, JUST_MELEE));
        collectibles.add(collectableItem);

        //-----------------------------mission deck--------------------------------------
         Deck deck1=new Deck();
         decks.add(deck1);
         deck1.setHero(heros.get(0));
         deck1.addCard(spells.get(0)).addCard(spells.get(6)).addCard(spells.get(9)).addCard(spells.get(9))
                 .addCard(spells.get(10)).addCard(spells.get(11)).addCard(spells.get(17)).addCard(spells.get(19))
                 .addCard(minions.get(0)).addCard(minions.get(8)).addCard(minions.get(10)).addCard(minions.get(10))
                 .addCard(minions.get(12)).addCard(minions.get(16)).addCard(minions.get(17)).addCard(minions.get(20))
                 .addCard(minions.get(21)).addCard(minions.get(25)).addCard(minions.get(37)).addCard(minions.get(35))
                 .addCard(minions.get(39)).addItem(items.get(0));

        Deck deck2=new Deck();
        decks.add(deck2);
        deck2.setHero(heros.get(4));
        deck2.addCard(spells.get(1)).addCard(spells.get(2)).addCard(spells.get(4)).addCard(spells.get(8))
                .addCard(spells.get(7)).addCard(spells.get(12)).addCard(spells.get(18))
                .addCard(minions.get(1)).addCard(minions.get(2)).addCard(minions.get(4)).addCard(minions.get(7))
                .addCard(minions.get(11)).addCard(minions.get(14)).addCard(minions.get(14)).addCard(minions.get(18))
                .addCard(minions.get(22)).addCard(minions.get(26)).addCard(minions.get(29)).addCard(minions.get(32))
                .addCard(minions.get(38)).addItem(items.get(9));

        Deck deck3=new Deck();
        decks.add(deck3);
        deck3.setHero(heros.get(6));
        deck3.addCard(spells.get(5)).addCard(spells.get(9)).addCard(spells.get(11)).addCard(spells.get(13))
                .addCard(spells.get(14)).addCard(spells.get(15)).addCard(spells.get(16))
                .addCard(minions.get(24)).addCard(minions.get(27)).addCard(minions.get(28)).addCard(minions.get(30))
                .addCard(minions.get(33)).addCard(minions.get(5)).addCard(minions.get(6)).addCard(minions.get(9))
                .addCard(minions.get(13)).addCard(minions.get(15)).addCard(minions.get(15)).addCard(minions.get(19))
                .addCard(minions.get(23)).addItem(items.get(4));

    }

}