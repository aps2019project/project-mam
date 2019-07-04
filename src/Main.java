import gson.GsonReader;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import view.pages.*;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

public class Main extends Application {
    private int port = 8000;
    private String hostName = "127.0.0.1";
    //private String hostName = "213.233.188.27";
    //private String hostName = "172.17.1.226";
    //private String hostName = "81.31.160.11";

    private static Socket server;

    private static DataInputStream input;
    private static DataOutputStream output;

    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("connecting to server ...");
            server = new Socket(hostName, port);
            System.out.println("connected to server: " + server.getLocalPort());

            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());
        }catch (ConnectException e){
            e.printStackTrace();
        }


            primaryStage.setTitle("Duelyst");

            Page.setStage(primaryStage);
            Page.setInput(input);
            Page.setOutput(output);
            Page.setServer(server);

        /*GsonWriter.writeCards();
        GsonWriter.writeItems();*/


            //GsonReader.readUser();
            //GsonReader.initShop();
            Page.getPages().push(new SignIn());
            //Page.getPages().push(new MainMenuPage());
            //Page.getPages().push(new ShopMenuPage());
            //Page.getPages().push(new CollectionMenuPage());
            //Page.getPages().push(new BattleMenuPage());
//        Page.getPages().push(new MainBattleMenuPage());
            //Page.getPages().push(new CustomMenuPage());
            primaryStage.show();

    }
}
