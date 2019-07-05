package gson;

import Model.Buffs.Buff;
import Model.card.Card;
import Model.deck.Deck;
import Model.game.Game;
import Model.item.CollectableItem;
import Model.item.Item;
import Model.item.UsableItem;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.clientCommand.ClientCommand;
import command.ServerCommand;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class GsonWriter {

    public static void writeUser(User user) throws IOException {
        FileWriter out = new FileWriter("gson/users/" + user.toString() + ".json");
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        out.write(gson.toJson(user));
        out.flush();
        out.close();
    }

    public static void writeCards() throws IOException {
        Shop.importCards();
        for (Card card : Shop.getSpells()) {
            FileWriter out = new FileWriter("gson/Cards/Spell/"+ card.getName() + ".json");
            Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                    .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
            out.write(gson.toJson(card));
            out.flush();
            out.close();
        }
    }

    public static void writeGame(Game game){

    }

    public static void writeCustomCard(Card card) throws IOException {
        StringBuilder path = new StringBuilder("gson/Cards/");
        switch (card.getCardType()){
            case "hero":
                path.append("Hero/");
                break;
            case "minion":
                path.append("Minion/");
                break;
            case "spell":
                path.append("Spell/");
        }
        path.append(card.getName()).append(".json");
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        FileWriter writer = new FileWriter(path.toString());
        writer.write(gson.toJson(card));
        writer.flush();
    }

    public static void writeItems() throws IOException {
        Shop.importCards();
        for (UsableItem item : Shop.getUsableItems()) {
            FileWriter writer = new FileWriter("gson/items/usable/" + item.getName() + ".json");
            Gson gson = new GsonBuilder().registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
            writer.write(gson.toJson(item));
            writer.flush();
            writer.close();
        }
        for (CollectableItem collectible : Shop.getCollectibles()) {
            FileWriter writer = new FileWriter("gson/items/collectible/" + collectible.getName() + ".json");
            Gson gson = new GsonBuilder().registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
            writer.write(gson.toJson(collectible));
            writer.flush();
            writer.close();
        }
    }

    public static void writeCustomUsableItem(Item item){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .create();
        try {
            FileWriter writer = new FileWriter("gson/items/usable/" + item.getName() + ".json");
            writer.write(gson.toJson(item));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeDeck(Deck deck) throws IOException {
        FileWriter writer = new FileWriter("gson/decks/" + deck.getName() + ".json");
        Gson gson = new GsonBuilder().registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor()).create();
        writer.write(gson.toJson(deck));
        writer.flush();
        writer.close();
    }

    public static void sendClientCommand(ClientCommand command, DataOutputStream out){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .create();
        try {
            out.writeUTF(gson.toJson(command));
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendServerCommand(ServerCommand command, DataOutputStream out){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Card.class, new CardAdapter())
                .registerTypeAdapter(Buff.class, new BuffAdaptor())
                .registerTypeAdapter(ClientCommand.class, new ClientCommandAdaptor())
                .create();
        try {
            out.writeUTF(gson.toJson(command));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendLargeFile(String object, DataOutputStream out){
        byte[] bytes = object.getBytes();
        BufferedOutputStream buffer =
                new BufferedOutputStream(out);

        int len = bytes.length;
        try {
            buffer.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*while (len > 0) {
            len--;
            buffer.write();
        }*/
    }
}
