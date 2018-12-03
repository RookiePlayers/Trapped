package Maze;

import inventory.Models.Inventory;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Player extends Actor {
    private int health=3;
    private long speed=2000;




    private Inventory inventory;

    public Player() {



    }

    public Player(String name, String url, int health, Inventory inventory) {

        super.name=name;
        super.url=url;
        this.health = health;
        this.inventory = inventory;

    }

    public Player(int health, Inventory inventory) {
        this.health = health;
        this.inventory = inventory;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
