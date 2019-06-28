import gson.GsonReader;
import javafx.application.Application;
import javafx.stage.Stage;

import view.pages.*;

public class Main extends Application {
    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
    }
}
