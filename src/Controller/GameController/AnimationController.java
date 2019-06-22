package Controller.GameController;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AnimationController {
    private static final AnimationController instance = new AnimationController();

    private AnimationController() {
    }

    public static AnimationController getInstance() {
        return instance;
    }

    public void moveTo(ImageView image, double x, double y) {
        //TODO : init path
        Path path = new Path(new MoveTo(image.getX() + image.getFitWidth() / 2, image.getY() + image.getFitHeight() / 2), new LineTo(x, y));
        //path.getElements().add(new CubicCurveTo(180, 60, 250, 340, image.getX() + 200, image.getY()));
        PathTransition ptr = new PathTransition(Duration.seconds(4), path);
        ptr.setNode(image);
        try {
            image.setImage(new Image(new FileInputStream("Fx/resource/Katara_run.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ptr.setOnFinished(event -> {
            try {
                image.setImage(new Image(new FileInputStream("Fx/resource/Katara_breathing.gif")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        ptr.play();
    }

    public void Attack(){}

    public void SpecialPower(){}

    public void Spell(){}
}
