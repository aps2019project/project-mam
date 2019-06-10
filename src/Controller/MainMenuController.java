package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import view.pages.MainMenuPage;

import java.io.FileInputStream;

public class MainMenuController {
    public ImageView battle;
    public ImageView shop;
    public ImageView collection;
    public ImageView custom;
    public ImageView logout;
    public ImageView exit;

    public MainMenuController() {
        //init();
    }

    private void init(){
        try {
           /* battle.setOnMouseClicked(event -> {
                System.out.println("clicked");
            });*/
           /*battle.setImage(new Image(new FileInputStream("resources/ui/button_primary_right.png")));

            MainMenuPage.getRoot().getChildren().addAll(battle);*/


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
