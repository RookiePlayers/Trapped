package Maze.MazeDifficulty;

import inventory.Models.Gem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

enum TILETYPE {T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, GOAL, GOALA, GOALB, BLOCKED}

public class Tile extends StackPane {
    //private Item item;
    private Map<String, Tile> exits = new HashMap<>();
    private ArrayList<PositionedItem> itemsInRoom = new ArrayList<>();
    private String image;
    private String path = "/Images/";
    private String ID = "a";
    private boolean moveAble = true;
    private boolean trap = false;
    private boolean trapper = false;
    private TILETYPE tileType = TILETYPE.T4;
    private final ImageView tileImg = new ImageView();
    private TILETYPE original_TileType;
    private ArrayList<Gem> gems = new ArrayList<>();

    public TILETYPE getTileType() {
        return tileType;
    }

    public void setTileType(TILETYPE tileType) {
        this.tileType = tileType;

    }

    public boolean isTrapper() {
        return trapper;
    }

    public void setTrapper(boolean trapper) {
        this.trapper = trapper;
    }

    public boolean isTrap(Tile t) {
        System.out.println("Are you a trapper? " + t.isTrapper());
        System.out.println("Am I Trapped? " + trap);
        if (t.isTrapper() && trap)
            return true;
        else return false;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    public TILETYPE getOriginal_TileType() {
        return original_TileType;
    }

    public void setOriginal_TileType(TILETYPE original_TileType) {
        this.original_TileType = original_TileType;
        this.tileType = original_TileType;
    }

    public boolean isMoveAble() {
        return moveAble;
    }

    public void setMoveAble(boolean moveAble) {
        this.moveAble = moveAble;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Tile(String image, String ID) {
        this.image = image;
        this.ID = ID;
        tileImg.setImage(new Image(getClass().getResourceAsStream(path + image)));
        getChildren().add(tileImg);


    }

    public void addItem(PositionedItem item) {
        itemsInRoom.add(item);
        getChildren().add(1, item);

    }

    public void removeItem(PositionedItem item) {
        itemsInRoom.remove(item);
        getChildren().remove(item);

    }

    public void addObstacle(PositionedObstacle obstacle, TILETYPE t) {
        getChildren().add(obstacle);

        this.tileType = t;

    }

    public void addObstacle(PositionedObstacle obstacle) {
        getChildren().add(obstacle);


    }

    public void removeObstacle(PositionedObstacle obstacle) {
        getChildren().remove(obstacle);
        this.tileType = original_TileType;

    }

    public Map<String, Tile> getExits() {
        return exits;
    }


    public void setExits(Tile north, Tile east, Tile south, Tile west) {
        if (north != null)
            exits.put("north", north);
        if (east != null)
            exits.put("east", east);
        if (south != null)
            exits.put("south", south);
        if (west != null)
            exits.put("west", west);

        if (north != null && south != null && east != null && west == null) {
            this.tileType = TILETYPE.T1;
        } else if (west != null && south != null && east != null && north == null) {
            this.tileType = TILETYPE.T2;
        } else if (north != null && south != null && west != null && east == null) {
            this.tileType = TILETYPE.T3;
        } else if (west != null && north != null && south != null && east != null) {
            this.tileType = TILETYPE.T4;
        } else if (north != null && west != null && east != null && south == null) {
            this.tileType = TILETYPE.T5;
        } else if (west == null && east != null && south == null && north == null) {
            this.tileType = TILETYPE.T6;
        } else if (east == null && west != null && south == null && north == null) {
            this.tileType = TILETYPE.T7;
        } else if (south != null && east == null && north == null && west == null) {
            this.tileType = TILETYPE.T8;
        } else if (east == null && north == null && west != null && south != null) {
            this.tileType = TILETYPE.T9;
        } else if (east != null && south != null && north == null && west == null) {
            this.tileType = TILETYPE.T10;
        } else if (east != null && south == null && north != null && west == null) {
            this.tileType = TILETYPE.T11;
        } else if (east == null && south == null && north != null && west != null) {
            this.tileType = TILETYPE.T12;
        } else if (east == null && south == null && north != null && west == null) {
            this.tileType = TILETYPE.T13;
        } else if (east != null && west != null && north == null && south == null) {
            this.tileType = TILETYPE.GOAL;
        }
        original_TileType = tileType;
    }

    public Tile nextTile(String dir) {
        System.out.println("Exit at: " + ID);
        if (exits.get(dir) != null) {
            return exits.get(dir);
        } else return null;

    }


    public void RemoveItem() {

    }

    public ArrayList<PositionedItem> getItemsInRoom() {
        return itemsInRoom;
    }

    public ArrayList<Gem> getGems() {
        return gems;
    }

    public void setItemsInRoom(ArrayList<PositionedItem> itemsInRoom) {
        this.itemsInRoom = itemsInRoom;
    }

    public String getImageFile() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        tileImg.setImage(new Image(getClass().getResourceAsStream(path + image)));

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
