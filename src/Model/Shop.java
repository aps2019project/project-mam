package Model;

import Controller.MenuController;

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

        card = new Spell("poison Lake", 900, 5, THREE_IN_THREE);
        card.addBuff(new Buff(CELL_EFFECT_POSION, 1, 1));
        cards.add(card);

        card = new Spell("Madness", 650, 0, ONE_INSIDER_FORCE);
        card.addBuff(new Buff(ATTACK_POWER, 3, 4));
        card.addBuff(new Buff(DISARM, 3, 0));
        cards.add(card);

        card  = new Spell("All Disarm", 2000, 9, ALL_ENEMY_FORCES);
        card.addBuff(new Buff(DISARM, 1, 0));
        cards.add(card);

        card = new Spell("All Poison", 1500, 8, ALL_ENEMY_FORCES);
        card.addBuff(new Buff(POSION, 4, 0));
        cards.add(card);

        card = new Spell("Health with profit", 2250, 0, ONE_INSIDER_FORCE);
        card.addBuff(new Buff(HEALTH_WEAKNESS, 0, 1));
        card.addBuff(new Buff(HOLY, 3, 2));
        cards.add(card);

        card = new Spell("Power Up", 2500, 2, ONE_INSIDER_FORCE);
        card.addBuff(new Buff(ATTACK_POWER, 0, 6));
        cards.add(card);


        //----------------------------minion-----------------------

        Spell spell = new Spell();

        card = new Minion("kamandar fars",300, 2, 6, 4, 7, RANGED, null);
        cards.add(card);

        card = new Minion("shamshirZan fars", 400, 2, 4, 6, 0, MELEE, ON_ATTACK);
        card.addBuff(new Buff(STUN,1,0));
        cards.add(card);

        card = new Minion("neyzehDar fars", 500, 1, 5, 3, 0, HYBRID, NULL);
        cards.add(card);

        card = new Minion("asbSavar fars", 200, 4,10, 6, 0, MELEE, NULL);
        cards.add(card);

        card = new Minion("pahlavan fars", 600, 9, 24, 6, 0, MELEE, ON_ATTACK);
        //???????????????????????
        cards.add(card);







        //-----------------------------hero-----------------------------------------

        card = new Hero("div sfid", 8000, 50, 4, MELEE, 0, 1,2);
        card.addBuff(new Buff(ATTACK_POWER, 100, 4));
        cards.add(card);

        card = new Hero("simorgh", 9000, 50, 4, MELEE, 0, 5, 8);
        //card.addBuff(new Buff(""));


    }
}


















