import gson.GsonReader;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import view.pages.*;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class Main extends Application {
    private int port = 8000;
    private String hostName = "127.0.0.1";
    private Socket server;

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
            System.out.println("connected to " + server.getLocalPort());

            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());
        }catch (ConnectException e){
            e.printStackTrace();
        }


            primaryStage.setTitle("Duelyst");

            Page.setStage(primaryStage);
            Page.setInput(input);
            Page.setOutput(output);

        /*GsonWriter.writeCards();
        GsonWriter.writeItems();*/


            GsonReader.readUser();
            GsonReader.initShop();
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
