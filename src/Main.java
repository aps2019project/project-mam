import javafx.application.Application;
import javafx.stage.Stage;
import view.SignIn;
import view.View;

public class Main extends Application {
    public static void main(String argc[]) {
        launch(argc);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new SignIn(primaryStage);
        primaryStage.show();
    }
}
