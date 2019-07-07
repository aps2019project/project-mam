package server;

import Controller.ServerCtrl;
import Model.card.Card;
import Model.shop.Shop;
import Model.user.User;
import com.google.gson.Gson;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server extends Application {

    private static int port = 8000;

    private static HashMap<String, ClientHandler> clients = new HashMap<>();
    private static HashMap<ClientHandler, ClientHandler> games = new HashMap<>();

    public static void main(String[] args) throws IOException {
        launch();
    }

    public static HashMap<String, ClientHandler> getClients() {
        return clients;
    }

    public static HashMap<ClientHandler, ClientHandler> getGames() {
        return games;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        runServer();
        primaryStage.setTitle("Server");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/layout/ServerPage.fxml"));
        Pane root = fxmlLoader.load();
        ServerCtrl controller = fxmlLoader.getController();
        controller.shopLbl.setText(Shop.getInstance().show());
        setBackGround(root, "resources/codex/codex_background.jpg");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected void setBackGround(Pane root, String address) {
        try {
            Image image = new Image(new FileInputStream(address));
            BackgroundImage myBI = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(myBI));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void runServer() {
        new Thread(() -> {
            try {
                ServerSocket server = new ServerSocket(port);
                System.out.println("server started");
                initServer();
                while (true) {
                    Socket client = server.accept();
                    System.out.println("a new client connected: " + client.getInetAddress() + " / " + client.getPort());
                    new ClientHandler(client).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void initServer() throws FileNotFoundException {
        GsonReader.readUser();
        GsonReader.initShop();
        GsonReader.readLastGames();
    }

    public static String showGames(){
        int counter = 1;
        StringBuilder info = new StringBuilder();
        info.append("Games:\n");
        for (Map.Entry<ClientHandler, ClientHandler> entry : games.entrySet()) {
            info.append("\n").append(counter++).append(" - ").append(entry.getKey().getUser().getName()).append(" Vs. ").append(entry.getValue().getUser().getName());
        }
        return info.toString();
    }
}
