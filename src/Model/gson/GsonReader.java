package Model.gson;

import Model.Buffs.Buff;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import Model.deck.Deck;
import Model.item.CollectableItem;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GsonReader {

    public static void readUser() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;
        try {
            File dir = new File("gson/users");
            for (File file : dir.listFiles()) {
                reader = new JsonReader(new FileReader(file));
                User.getUsers().add(gson.fromJson(reader, User.class));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void initShop() throws FileNotFoundException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        JsonReader reader;
        File dir = new File("gson/Cards/Hero");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getCards().add(gson.fromJson(reader, Hero.class));
        }
        dir = new File("gson/Cards/Minion");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getCards().add(gson.fromJson(reader, Minion.class));
        }
        dir = new File("gson/Cards/Spell");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getCards().add(gson.fromJson(reader, Spell.class));
        }
        readItems();
        readDeck("mission_1");
        readDeck("mission_2");
        readDeck("mission_3");
    }

    public static void readDeck(String name) throws FileNotFoundException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        JsonReader reader;
        reader = new JsonReader(new FileReader("gson/decks/" + name + ".json"));
        Shop.getDecks().add(gson.fromJson(reader, Deck.class));
    }

    public static Deck getDeck(String name) throws FileNotFoundException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        JsonReader reader;
        reader = new JsonReader(new FileReader("gson/decks/" + name + ".json"));
        return gson.fromJson(reader, Deck.class);
    }

    public static void readItems() throws FileNotFoundException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        JsonReader reader;
        File dir = new File("gson/items/usable");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getUsableItems().add(gson.fromJson(reader, UsableItem.class));
        }
        dir = new File("gson/items/collectible");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getCollectibles().add(gson.fromJson(reader, CollectableItem.class));
        }
    }

}
