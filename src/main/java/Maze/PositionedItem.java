package Maze;

import inventory.Models.Item;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PositionedItem extends VBox {
    private Item item;
    private Coord coordinates;

    public PositionedItem(Item item) {
        this.item = item;
        this.coordinates = new Coord(0,0);

        getChildren().addAll(item);
        setAlignment(Pos.CENTER);

        setTranslateX(coordinates.getX());
        setTranslateY(coordinates.getY());
        setMouseTransparent(false);
        setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);


    }
    public PositionedItem(Item item, Coord coordinates) {
        this.item = item;
        this.coordinates = coordinates;

        getChildren().addAll(item);
        setAlignment(Pos.CENTER);

        setTranslateX(coordinates.getX());
        setTranslateY(coordinates.getY());
        setMouseTransparent(false);
        setMaxSize(Region.USE_PREF_SIZE,Region.USE_PREF_SIZE);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Coord getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coord coordinates) {
        this.coordinates = coordinates;
    }
}
