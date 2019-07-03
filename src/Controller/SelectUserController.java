package Controller;

import command.CommandType;
import command.Result;
import command.ServerCommand;
import command.clientCommand.ChatCmd;
import command.clientCommand.ExitGameCmd;
import command.clientCommand.RequestGameCmd;
import gson.GsonReader;
import gson.GsonWriter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import server.Server;
import view.BattleMenu.BattleMenuPage;
import view.pages.Page;

public class SelectUserController {

    public ImageView back;

    public Button start;

    public Label label;

    public ScrollPane chatList;
    public GridPane chatGrid;
    public TextField message;
    public Button send;

    public int count = 0;
    private boolean isGameCreated = false;

    public void setSend() {
        showMyMessage();
        GsonWriter.sendClientCommand(new ChatCmd(message.getText()), Page.getOutput());
    }

    public void showMyMessage() {
        chatGrid.add(new Label(message.getText()), 1, count++);
    }

    public void showOthersMessage(ServerCommand command) {
                Platform.runLater(() -> {
                    chatGrid.add(new Label(command.getUserName() + ": " + command.getMessage()), 0, count++);
                });
    }

    @FXML
    public void setStart() {
        label.setText("searching for an opponent ...");
        GsonWriter.sendClientCommand(new RequestGameCmd(BattleMenuPage.getGameMood(),
                BattleMenuPage.getFlags()), Page.getOutput());
    }

    @FXML
    public void setBack() {
        exit();
        Page.getPages().pop();
        Page.getPages().peek().start();
    }

    public void request(ServerCommand command) {
        if (command.getType() != CommandType.CREATE_GAME)
            return;
        if (command.getResult() == Result.SUCCESSFUL) {
            BattleMenuPage.setSecondUser(command.getUser());
            BattleMenuPage.setBaseTurn(command.getBaseTurn());
            isGameCreated = true;
        } /*else {
            command = GsonReader.getServerCommand(Page.getInput());
            if (command.getType() == CommandType.EXIT_GAME)
                return;
            BattleMenuPage.setSecondUser(command.getUser());
            BattleMenuPage.setBaseTurn(command.getBaseTurn());
            Platform.runLater(BattleMenuPage::createMultiGame);
        }*/
    }

    public void handleServerCmd() {
        new Thread(() -> {
            while(true) {
                ServerCommand command = GsonReader.getServerCommand(Page.getInput());
                request(command);
                if (command.getType() == CommandType.CHAT)
                    showOthersMessage(command);
                if (isGameCreated){
                    Platform.runLater(BattleMenuPage::createMultiGame);
                    break;
                }
            }
        }).start();
    }

    public void exit() {
        GsonWriter.sendClientCommand(new ExitGameCmd(), Page.getOutput());
    }
}
