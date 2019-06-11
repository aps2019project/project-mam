package view.pages;

import Controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShopMenuPage extends Page {
    View view = View.getInstance();
    private static Pane root = new Pane();
    private ShopController controller;

    public ShopMenuPage() {
        start();
    }

    @Override
    public void start() {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../layout/Shop.fxml"));
        root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        initializeImage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeImage() throws FileNotFoundException {
        setBackGround(root, "resources/codex/chapter6_background@2x.jpg");
        setBackGround(controller.CResult_lb, "resources/ui/motion_streak.png");
        setBackGround(controller.SResult_lb, "resources/ui/motion_streak.png");
        setBackGround(controller.collection_lb, "resources/ui/button_secondary_glow@2x.png");
        setBackGround(controller.store_lb, "resources/ui/button_secondary_glow@2x.png");
        controller.back.setImage(new Image(new FileInputStream("resources/ui/button_back_corner@2x.png")));


    }

    public ShopMenuPage(Stage stage) {
        super(stage);
    }



    @Override
    public void help() {
        view.showHelpForShopMenu();
    }



    @Override
    public void showMenu() {
        view.show("----------<Shop>---------");
    }
}
