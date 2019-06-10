package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.mainPages.Page;

import java.io.IOException;

public class SignIn extends Page {
    private View view = View.getInstance();
    public ImageView imageView=new ImageView();

    public SignIn(Stage stage) {
        super(stage);
        start();
        stage.show();
    }

    public SignIn(){
        start();
    }

    public void start() {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("FXML/SignIn.fxml"));
            setBackGround(root,"resources/codex/chapter17_background@2x.jpg", 500, 250);
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void help() {
        view.showHelpForAccountMenu();
    }


    /*public void handleCommand(String command) {
        if (command.matches("create account .*"))
            controller.createAccount(command);
        else if (command.matches("login .*"))
            controller.loginAccount(command);
        else if (command.matches("show leaderboard"))
            controller.showLeaderBoard();
        else if (command.matches("exit"))
            view.exit();
        else if (command.matches("help"))
            view.showHelpForAccountMenu();
        else
            view.printError(ErrorType.INVALID_COMMAND);

    }*/

    @Override
    public void showMenu() {
        view.show("----------<Account>---------");
    }
}
