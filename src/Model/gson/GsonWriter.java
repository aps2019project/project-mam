package Model.gson;

import Model.card.Card;
import Model.deck.Deck;
import Model.item.CollectableItem;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GsonWriter {

    public static void writeUser(User user) throws IOException {
        FileWriter out = new FileWriter("gson/users/" + user.toString() + ".json");
        Gson gson = new GsonBuilder().create();
        out.write(gson.toJson(user));
        out.flush();
        out.close();
    }

    public static void writeCards() throws IOException {
        Shop.importCards();
        for (Card card : Shop.getMinions()) {
            FileWriter out = new FileWriter("gson/Cards/Minion/"+ card.getName() + ".json");
            Gson gson = new GsonBuilder().create();
            out.write(gson.toJson(card));
            out.flush();
            out.close();
        }
    }

    public static void writeItems() throws IOException {
        Shop.importCards();
        for (UsableItem item : Shop.getUsableItems()) {
            FileWriter writer = new FileWriter("gson/items/usable/" + item.getName() + ".json");
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(item));
            writer.flush();
            writer.close();
        }
        for (CollectableItem collectible : Shop.getCollectibles()) {
            FileWriter writer = new FileWriter("gson/items/collectible/" + collectible.getName() + ".json");
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(collectible));
            writer.flush();
            writer.close();
        }
    }

    public static void writeDeck(Deck deck) throws IOException {
        FileWriter writer = new FileWriter("gson/decks/" + deck.getName() + ".json");
        Gson gson = new GsonBuilder().create();
        writer.write(gson.toJson(deck));
        writer.flush();
        writer.close();
    }
}
