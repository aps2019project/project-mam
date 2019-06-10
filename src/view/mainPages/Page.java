package view.mainPages;

import Controller.Controller;
import javafx.stage.Stage;

public abstract class Page {
     protected Controller controller;
     protected Stage stage;

    public Page(Stage stage) {
        this.controller = Controller.getInstance();
        this.stage = stage;
    }

    public Page() {
    }

    public void start(){}
    public void help(){}
     public void handleCommand(String command){}
     public void showMenu(){}
}
