import gson.GsonReader;
import javafx.application.Application;
import javafx.stage.Stage;

import view.pages.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Main extends Application {
    private int port = 8000;
    private String hostName = "127.0.0.1";
    private Socket server;

    private InputStream input;
    private OutputStream output;

    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            System.out.println("connecting to server ...");
            server = new Socket(hostName, port);
            System.out.println("connected to " + server.getLocalPort());

            input = server.getInputStream();
            output = server.getOutputStream();


            primaryStage.setTitle("Duelyst");
            Page.setStage(primaryStage);

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
        } catch (ConnectException e) {
            System.out.println("connection failed!");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
