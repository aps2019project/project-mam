package gson;

import Model.Buffs.Buff;
import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import Model.deck.Deck;
import Model.game.Game;
import Model.game.LastGame;
import Model.item.CollectableItem;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import command.clientCommand.ClientCommand;
import command.ServerCommand;

import java.io.*;

public class GsonReader {

    public static void readUser() throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        JsonReader reader;
        try {
            File dir = new File("gson/users");
            for (File file : dir.listFiles()) {
                reader = new JsonReader(new FileReader(file));
                User.getUsers().add(gson.fromJson(reader, User.class));
            }
        } catch (NullPointerException e) {
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

    public static ServerCommand getServerCommand(DataInputStream in) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .create();
        try {
            //return gson.fromJson(in.readUTF(), ServerCommand.class);
            return gson.fromJson(receive(in), ServerCommand.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ClientCommand getClientCommand(DataInputStream in) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .registerTypeAdapter(ClientCommand.class, new ClientCommandAdaptor())
                .create();
        try {
            //return gson.fromJson(in.readUTF(), ClientCommand.class);
            return gson.fromJson(receive(in), ClientCommand.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void readLastGames(){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .registerTypeAdapter(ClientCommand.class, new ClientCommandAdaptor())
                .create();
        DataInputStream reader = null;
        File dir = new File("gson/lastGame");
        for (File file : dir.listFiles()) {
            try {
                reader = new DataInputStream(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            LastGame.getLastGames().add(gson.fromJson(receive(reader), LastGame.class));
        }
    }

    public static Game readGame(String name){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .registerTypeAdapter(ClientCommand.class, new ClientCommandAdaptor())
                .create();
        DataInputStream reader = null;
        try {
            reader = new DataInputStream(new FileInputStream("gson/Game/" + name + ".json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gson.fromJson(receive(reader), Game.class);
    }

    private static String receive(DataInputStream in) {
        StringBuilder file = new StringBuilder();
        try {
            String size = in.readUTF();
            int count = 1;
            while (count < Integer.parseInt(size)){
                file.append(in.readUTF());
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.toString();
    }

}
