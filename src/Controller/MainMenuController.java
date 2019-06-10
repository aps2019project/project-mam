package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.MainMenuPage;

import java.io.FileInputStream;

public class MainMenuController {
    public ImageView battle ;
    public ImageView shop = new ImageView();
    public ImageView collection = new ImageView();
    public ImageView custom = new ImageView();
    public ImageView logout = new ImageView();
    public ImageView exit = new ImageView();

    public MainMenuController() {
        init();
    }

    private void init(){
        try {
           battle.setImage(new Image(new FileInputStream("resources/ui/button_primary_right.png")));

            MainMenuPage.getRoot().getChildren().addAll(battle);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
