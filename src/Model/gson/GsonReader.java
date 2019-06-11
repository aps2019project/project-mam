package Model.gson;

import Model.card.Card;
import Model.item.Item;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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
        Gson gson = new Gson();
        JsonReader reader;
        File dir = new File("gson/Cards");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getCards().add(gson.fromJson(reader, Card.class));
        }
        readItems();
    }

    public static void readDeck(){

    }

    public static void readItems() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader;
        File dir = new File("gson/items");
        for (File file : dir.listFiles()) {
            reader = new JsonReader(new FileReader(file));
            Shop.getItems().add(gson.fromJson(reader, UsableItem.class));
        }
    }

}
