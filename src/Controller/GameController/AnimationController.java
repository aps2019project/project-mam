package Controller.GameController;

import Model.card.Card;
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
        Path path = new Path(new MoveTo(image.getX() + image.getFitWidth() / 2, image.getY() + image.getFitHeight() / 2),
                new LineTo(x, y));
        //path.getElements().add(new CubicCurveTo(180, 60, 250, 340, image.getX() + 200, image.getY()));
        PathTransition ptr = new PathTransition(Duration.seconds(1), path);
        ptr.setNode(image);
        try {
            image.setImage(new Image(new FileInputStream("resources/gif/Katara_run.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ptr.setOnFinished(event -> {
            try {
                image.setImage(new Image(new FileInputStream("resources/gif/Katara_breathing.gif")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        ptr.play();
    }

    public void attack(ImageView image, Card attacker) {
        //TODO : init path
        new Thread(() -> {
            try {
                image.setImage(new Image(new FileInputStream("resources/gif/Katara_attack.gif")));
                Thread.sleep(1000);
                image.setImage(new Image(new FileInputStream("resources/gif/Katara_breathing.gif")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void conterAttack(ImageView image, int attackerId) {
        //TODO : init path
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                image.setImage(new Image(new FileInputStream("resources/gif/Katara_attack.gif")));
                Thread.sleep(1000);
                image.setImage(new Image(new FileInputStream("resources/gif/Katara_breathing.gif")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void SpecialPower() {
    }

    public void Spell() {
    }
}
