package Model;

import java.util.ArrayList;

import static Model.TargetCommunity.*;
import static Model.BuffType.*;

public class Shop {
    private static final Shop SHOP = new Shop();

    private static ArrayList<Card> cards = new ArrayList<>();

    private Shop() {
    }

    public static Shop getInstance() {
        return SHOP;
    }

    public static void importCards() {
        Card card = new Spell("Total Disarm", 1000, 0, ONE_ENEMY_FORCE);
        card.addBuff(new Buff(DISARM, 100, 0));
        cards.add(card);

        card = new Spell("Empower", 250, 1, ONE_INSIDER_FORCE);
        card.addBuff(new Buff(ATTACK_POWER, 100, 2));
        cards.add(card);

        card = new Spell("Fire Ball", 400, 1, ONE_ENEMY_FORCE);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 0, 4));
        cards.add(card);

        card = new Spell("God Strength", 450, 2, ONE_INSIDER_FORCE);
        card.addBuff(new Buff(ATTACK_POWER, 100, 4));
        cards.add(card);

        card = new Spell("Hell Fire", 600, 3, TWO_IN_TWO);
        card.addBuff(new Buff(CELL_EFFECT_FIERY, 2, 2));
        cards.add(card);

        card = new Spell("Lighting Bolt", 1250, 2, ONE_ENEMY_FORCE);
        card.addBuff(new Buff(ATTACK_TO_ENEMY, 0, 8));
        cards.add(card);

        card = new Spell("poison Bolt", 900, 5, THREE_IN_THREE);
        card.addBuff(new Buff(CELL_EFFECT_POSION, 1, 1));
        cards.add(card);

    }
}


















