package Maze;

import inventory.Models.Inventory;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

public class Player extends Actor {
    private int health=100;
    private ProgressBar healthbar;


    public ProgressBar getHealthbar() {
        return healthbar;
    }

    public void setHealthbar(ProgressBar healthbar) {
        this.healthbar = healthbar;
    }

    private
    Inventory inventory;

    public Player() {
        healthbar=new ProgressBar();
        healthbar.setProgress(health);


    }

    public Player(String name, String url, int health, Inventory inventory) {

        super.name=name;
        super.url=url;
        this.health = health;
        this.inventory = inventory;
        healthbar=new ProgressBar();
        healthbar.setProgress(health);
    }

    public Player(int health, Inventory inventory) {
        this.health = health;
        this.inventory = inventory;
        healthbar=new ProgressBar();
        healthbar.setProgress(health);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        healthbar=new ProgressBar();
        healthbar.setProgress(health);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
