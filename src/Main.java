import Model.gson.GsonReader;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;
import view.View;
import view.pages.*;

public class Main extends Application {
    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Duelyst");

        Page.setStage(primaryStage);
        GsonReader.readUser();
        Page.getPages().push(new SignIn());
        //Page.getPages().push(new MainMenuPage());
        //Page.getPages().push(new ShopMenuPage());
        //Page.getPages().push(new CollectionMenuPage());
        primaryStage.show();
    }
}
