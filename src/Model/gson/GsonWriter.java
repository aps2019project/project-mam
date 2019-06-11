package Model.gson;

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
}
