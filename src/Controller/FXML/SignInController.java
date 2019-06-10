package Controller.FXML;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SignInController {
    public VBox vBox;
    public ImageView imageView;

    public void init() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("resources/main/codex/chapter17_background@2x.jpg"));
        imageView.setImage(image);
        imageView.setVisible(true);
    }
}
