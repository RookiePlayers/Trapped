package Maze;

import Interface.MazeInterface;
import Maze.MazeDifficulty.Maze;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

public abstract class MazeDecorator implements MazeInterface {
   protected Maze maze;
   public MazeDecorator(Maze maze)
   {
       this.maze=maze;
   }

    public String getTitle(){
       return maze.getTitle();
    }

    public void setTitle(String title){
        maze.setTitle(title);
    }

    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory)
    {
        return maze.initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);
    }
    public void startMaze(Scene scene)
    {
        maze.startMaze(scene);
    }
    public Item itemGenerator()
    {
       return  maze.itemGenerator();
    }

}
