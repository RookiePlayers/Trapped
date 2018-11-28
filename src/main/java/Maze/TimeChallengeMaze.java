package Maze;

import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public class TimeChallengeMaze extends MazeDecorator {
    public TimeChallengeMaze(Maze maze)
    {
        super(maze);
    }

    @Override
    public String getTitle(){
        return maze.getTitle();
    }
    @Override
    public void setTitle(String title){
        maze.setTitle(title);
    }
    @Override
    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory)
    {
        return maze.initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);
    }
    @Override
    public void startMaze(Scene scene)
    {
        maze.startMaze(scene);
    }
    @Override
    public Item itemGenerator()
    {
        return  maze.itemGenerator();
    }

}
