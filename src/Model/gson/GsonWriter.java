package Model.gson;

import Model.card.Card;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class GsonWriter {

    public static void writeUser(User user) throws IOException {
        FileWriter out = new FileWriter("gson/users/" + user.toString() +".json");
        Gson gson = new GsonBuilder().create();
        out.write(gson.toJson(user));
        out.flush();
        out.close();
    }

    public static void writeCards() throws IOException {
        Shop.importCards();
        for (Card card : Shop.getCards()) {
            FileWriter out = new FileWriter("gson/Cards/" + card.getName() + ".json");
            Gson gson = new GsonBuilder().create();
            out.write(gson.toJson(card));
            out.flush();
            out.close();
        }
    }

    public static void writeItems() throws IOException {
        Shop.importCards();
        for (UsableItem item : Shop.getItems()) {
            FileWriter writer = new FileWriter("gson/items/" + item.getName() + ".json");
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(item));
            writer.flush();
            writer.close();
        }
    }











}
