package Maze;

import inventory.Models.Obstacle;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PositionedObstacle extends VBox {
    private Obstacle obstacle;
    private Coord coordinates;

    public PositionedObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
        this.coordinates = new Coord(0,0);

        getChildren().addAll(obstacle);
        setAlignment(Pos.CENTER);

        setTranslateX(coordinates.getX());
        setTranslateY(coordinates.getY());
        setMouseTransparent(false);
        setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
    }
    public PositionedObstacle(Obstacle obstacle, Coord coordinates) {
        this.obstacle = obstacle;
        this.coordinates = coordinates;

        getChildren().addAll(obstacle);
        setAlignment(Pos.CENTER);

        setTranslateX(coordinates.getX());
        setTranslateY(coordinates.getY());
        setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Coord getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coord coordinates) {
        this.coordinates = coordinates;
        setTranslateX(coordinates.getX());
        setTranslateY(coordinates.getY());
    }
}
