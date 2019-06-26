package Model.size;

import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class Coordinate {
    private static final Coordinate instance = new Coordinate();

    private Coordinate(){
        resolution = Resolution.FULL_HD;
    }

    public static Coordinate getInstance(){
        return instance;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    private Resolution resolution;

    public void setHandCoordinate(ArrayList<Circle> hands){

    }

}
