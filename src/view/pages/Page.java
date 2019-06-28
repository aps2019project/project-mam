package view.pages;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;

public abstract class Page {
     protected static Stage stage;
     protected static Deque<Page> pages = new LinkedList<>();
    protected static DataInputStream input;
    protected static DataOutputStream output;

    public Page() {
    }

    public static DataInputStream getInput() {
        return input;
    }

    public static DataOutputStream getOutput() {
        return output;
    }

    public static void setInput(DataInputStream input) {
        Page.input = input;
    }

    public static void setOutput(DataOutputStream output) {
        Page.output = output;
    }

    public static void setStage(Stage stage) {
        Page.stage = stage;
    }

    public static Deque<Page> getPages() {
        return pages;
    }

    public static Stage getStage() {
        return stage;
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

    protected void setBackGround(Pane root, String address){
        try {
            Image image = new Image(new FileInputStream(address));
            BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
            root.setBackground(new Background(myBI));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void setBackGround(Pane root, Color color){
        root.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected void setBackGround(Label label, String address){
        try {
            Image image = new Image(new FileInputStream(address));
            BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT, new BackgroundSize(label.getWidth(), label.getHeight(), true, true, true, true));
            label.setBackground(new Background(myBI));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void start(){}

    public void help(){}
     public void handleCommand(String command){}
     public void showMenu(){}
}
