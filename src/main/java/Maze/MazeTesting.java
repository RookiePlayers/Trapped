package Maze;

import inventory.Models.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MazeTesting extends Application{
    public static void main(String[]args)
    {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        MazeFactory mazeFactory=new MazeFactory();
        Maze maze=mazeFactory.makeMaze(MAZETYPE.SIMPLE);
        primaryStage=new Stage();
        Group root=new Group();
        Scene sc=new Scene(root,500,500);
        KeyItem key = new KeyItem("gold_key", "Gold key", "", 50, 50, true);
        key.setPlayAnimation(true);
        HealthPotion hp = new HealthPotion("healthpotion", "+50 HP", "Adds 50 HP", 50, 50, true, 50);
        hp.setPlayAnimation(true);

        SpeedPotion sp = new SpeedPotion("speedpotion", "x2 Speed", "Doubles your Speed", 50, 50, true, 2);
        sp.setPlayAnimation(true);

        TimeFreeze tf = new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 20);
        tf.setPlayAnimation(true);


        ArrayList<Item> items = new ArrayList<Item>();
        items.add(key);
        items.add(hp);
        items.add(tf);
        items.add(sp);
        Inventory inventory=new Inventory(items,false);

       // root.getChildren().addAll(maze.initMaze(sc,new Player("olllie","/Images/hero.png",0,inventory),null,inventory));
        primaryStage.setScene(sc);
        primaryStage.show();

    }
}
