package Controller;

import Model.enums.ErrorType;
import Model.user.User;
import command.CommandType;
import command.Result;
import command.ServerCommand;
import command.clientCommand.ExitGameCmd;
import command.clientCommand.RequestGameCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import server.Server;
import view.BattleMenu.BattleMenuPage;
import view.BattleMenu.GameMoodMenuPage;
import view.pages.Page;

public class SelectUserController {

    public ImageView back;

    public Button start;

    public Label label;

    @FXML
    public void setStart() {
        label.setText("searching for an opponent ...");
        request();
    }

    @FXML
    public void setBack() {
        exit();
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void request() {
        new Thread(() -> {
            GsonWriter.sendClientCommand(new RequestGameCmd(BattleMenuPage.getGameMood(),
                    BattleMenuPage.getFlags()), Page.getOutput());
            ServerCommand command = GsonReader.getServerCommand(Page.getInput());
            if (command.getResult() == Result.SUCCESSFUL) {
                BattleMenuPage.setSecondUser(command.getUser());
                Platform.runLater(BattleMenuPage::createGame);
            } else {
                command = GsonReader.getServerCommand(Page.getInput());
                if (command.getType() == CommandType.EXIT_GAME)
                    return;
                BattleMenuPage.setSecondUser(command.getUser());
                Platform.runLater(BattleMenuPage::createGame);
            }
        }).start();

    }

    public void exit() {
        GsonWriter.sendClientCommand(new ExitGameCmd(), Page.getOutput());
    }
}
