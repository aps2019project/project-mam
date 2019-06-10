package view.mainPages;

import Controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;

public abstract class Page {
     protected Controller controller;
     protected static Stage stage;
     protected static Deque<Page> pages = new LinkedList<>();

    public Page(Stage stage) {
        this.controller = Controller.getInstance();
        this.stage = stage;
    }

    public Page() {
    }

    public static void setStage(Stage stage) {
        Page.stage = stage;
    }

    public static Deque<Page> getPages() {
        return pages;
    }

    protected void setBackGround(Pane root, String address, int width, int height){
        try {
            Image image = new Image(new FileInputStream(address),width,height,false, true);
            BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(myBI));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void start(){}
    public void help(){}
     public void handleCommand(String command){}
     public void showMenu(){}
}
