package Model.gson;

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
        ArrayList<User> users = new ArrayList<>();
        try {
            File dir = new File("gson/users");
            for (File file : dir.listFiles()) {
                reader = new JsonReader(new FileReader(file));
                users.add(gson.fromJson(reader, User.class));
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void readCards(){

    }

    public static void readDeck(){

    }

}
