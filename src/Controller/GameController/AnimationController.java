package Controller.GameController;

import Model.card.Card;
import config.Config;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AnimationController {
    private static final AnimationController instance = new AnimationController();

    private MapController mapController;

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    private AnimationController() {
    }

    public static AnimationController getInstance() {
        return instance;
    }

    public void moveTo(ImageView image, Card card, double x, double y) {
        //TODO : init path
        Path path = new Path(new MoveTo(image.getX() + image.getFitWidth() / 2, image.getY() + image.getFitHeight() / 2),
                new LineTo(x, y));
        //path.getElements().add(new CubicCurveTo(180, 60, 250, 340, image.getX() + 200, image.getY()));
        PathTransition ptr = new PathTransition(Duration.millis((double)1000 / Config.speed), path);
        ptr.setNode(image);
        try {
            image.setImage(new Image(new FileInputStream(card.getRunImage())));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ptr.setOnFinished(event -> {
            try {
                image.setImage(new Image(new FileInputStream(card.getBreathingImage())));
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
                image.setImage(new Image(new FileInputStream(attacker.getAttackImage())));
                Thread.sleep(2500 / Config.speed);
                image.setImage(new Image(new FileInputStream(attacker.getBreathingImage())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void counterAttack(ImageView image, Card attacker) {
        //TODO : init path
        new Thread(() -> {
            try {
                Thread.sleep(2500 / Config.speed);
                image.setImage(new Image(new FileInputStream(attacker.getAttackImage())));
                Thread.sleep(2500 / Config.speed);
                image.setImage(new Image(new FileInputStream(attacker.getBreathingImage())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void insertSpell(Rectangle cell, Card card, Pane pane) {
        //TODO : init path
        new Thread(() -> {
            try {
                ImageView image = new ImageView(new Image(new FileInputStream(card.getAttackImage())));
                image.setY(cell.getY() + 12);
                image.setX(cell.getX() + 12);
                image.setFitWidth(60);
                image.setFitHeight(60);
                Platform.runLater(() -> pane.getChildren().add(image));
                Thread.sleep(2000 / Config.speed);
                image.setImage(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void SpecialPower() {
    }


}
