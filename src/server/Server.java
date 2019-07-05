package server;

import Model.card.Card;
import Model.shop.Shop;
import com.google.gson.Gson;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server extends Application {

    private static int port = 8000;

    private static HashMap<String, ClientHandler> clients = new HashMap<>();

    public static void main(String[] args) throws IOException {
        launch();
    }

    private static void initServer() throws FileNotFoundException {
        GsonReader.readUser();
        GsonReader.initShop();
    }

    public static HashMap<String, ClientHandler> getClients() {
        return clients;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        runServer();
        Pane root = new FXMLLoader(getClass().getResource("../view/layout/ServerPage.fxml")).load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void runServer(){
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);
                System.out.println("server started");
                initServer();
                while (true){
                    Socket client = server.accept();
                    System.out.println("a new client connected: " + client.getInetAddress()+" / "+client.getPort());
                    new ClientHandler(client).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
