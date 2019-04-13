package Model;

import java.util.ArrayList;

import static Model.TargetCommunity.*;
import static Model.BuffType.*;
import static Model.ImpactType.*;
import static Model.SPActivationTime.*;

public class Shop {
    private static final Shop SHOP = new Shop();

    private static ArrayList<Card> cards = new ArrayList<>();


    private Shop() {
    }

    public static Shop getInstance() {
        return SHOP;
    }

    public static void importCards() {
        Card card = new Spell("Total Disarm", 1000, 0);
        card.addBuff(new Buff(DISARM, 100, 0, ONE_ENEMY_FORCE));
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

        card = new Spell("Health with profit", 2250, 0);
        card.addBuff(new Buff(HEALTH_WEAKNESS, 0, 1, ONE_INSIDER_FORCE));
        card.addBuff(new Buff(HOLY, 3, 2, ONE_INSIDER_FORCE));
        cards.add(card);

        card = new Spell("Power Up", 2500, 2);
        card.addBuff(new Buff(ATTACK_POWER, 0, 6, ONE_INSIDER_FORCE));
        cards.add(card);

        card = new Spell("Dispel", 2100, 0);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 0, 0, ALL_INSIDER_FORCES));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 0, 0, ALL_ENEMY_FORCES));
        cards.add(card);

        card = new Spell("Area Dispel", 2500, 2);
        card.addBuff(new Buff(REMOVE_INSIDERS_BUFFS, 0, 0, TWO_IN_TWO));
        card.addBuff(new Buff(REMOVE_ENEMIES_BUFFS, 0, 0, TWO_IN_TWO));
        cards.add(card);


        //----------------------------minions-----------------------


        card = new Minion("kamandar fars", 300, 2, 6, 4, 7, RANGED, null);
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


    }
}


















