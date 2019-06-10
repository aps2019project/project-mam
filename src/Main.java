import javafx.application.Application;
import javafx.stage.Stage;
import view.MainMenuPage;
import view.Page;
import view.SignIn;
import view.View;

public class Main extends Application {
    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Page.setStage(primaryStage);
//        Page.getPages().push(new SignIn());
        Page.getPages().push(new MainMenuPage());
        primaryStage.show();
    }
}
