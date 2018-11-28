package Maze;

import Interface.MazeInterface;
import inventory.Effects;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import javafx.animation.Animation;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;


public class MazeMenu extends Stage  {
    private Node top;
    private Node middle;
    private Node bottom;
    private Button back;
    private Button exit;
    private String title;
    private InventoryUI inventoryUI;
    private Item item = null;
    private ArrayList<Item>items =new ArrayList<>();
    private Inventory inventory;
    private Label invLabel=new Label("0");


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public MazeMenu(Inventory inventory,MAZETYPE mazeType,GAMEMODES gamemodes)
    {
        System.out.println("Playing "+gamemodes+" on "+mazeType+" Dificulty");
        Maze maze;
        this.inventory=inventory;
        Inventory startOffInventory=new Inventory();
        Player player=new Player("Player"+((int)(Math.random()*(1000-1))),"/Images/hero.png",100,startOffInventory);

        BorderPane borderPane=new BorderPane();
        borderPane.setPadding(new Insets(20));

        back=new Button("Back");
        exit=new Button("Exit");
        top=new HBox();

        MazeFactory mazeFactory=new MazeFactory();
        maze=mazeFactory.makeMaze(mazeType);
        setTitle(maze.getTitle());
        title=maze.getTitle();
        ((HBox) top).getChildren().addAll(exit,new Label(title),back);

        back.setAlignment(Pos.TOP_LEFT);
        exit.setAlignment(Pos.TOP_RIGHT);
        ((HBox) top).setAlignment(Pos.CENTER);
        ((HBox) top).setSpacing(20.0);



        bottom=new HBox();
        ((HBox) bottom).getChildren().addAll();

        Scene scene=new Scene(borderPane);
        setScene(scene);
        System.out.println(player.getName());


        inventoryUI = new InventoryUI(scene, 4);
        middle=maze.initMaze(scene,inventoryUI,invLabel,item,new Player("olllie","/Images/hero.png",0,inventory),null,inventory);
    invLabel.setText(inventoryUI.getItemCount()+"");

    Thread mazeThread=new Thread(maze);
    mazeThread.start();


        borderPane.setTop(top);
        borderPane.setBottom(bottom);
        borderPane.setCenter(middle);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setHeight(bounds.getHeight());
        setWidth(bounds.getWidth());


        scene.getStylesheets().add(getClass().getResource("/css/bag.css").toExternalForm());
        ImageView inventory_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/inventory.png")));
        inventory_img.setFitHeight(60);
        inventory_img.setFitWidth(70);
        HBox box = new HBox();

        Button inv= new Button("", inventory_img);
        box.getChildren().addAll(invLabel, inv);
        box.setAlignment(Pos.TOP_CENTER);
        inv.getStyleClass().add("inventoryBag");
        inv.setAlignment(Pos.TOP_RIGHT);
        inv.setEffect(Effects.DROP_SHADOW());
        inv.setOnAction(e -> {
            inventoryUI.reload();
            scene.setCursor(Cursor.DEFAULT);
            item = null;
            inventoryUI.showAndWait();
            item = inventoryUI.getItem();
            invLabel.setText(inventoryUI.getItemCount() + "");
            System.out.println("After: " + item);
        });
    ((HBox) top).getChildren().add(box);

    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }



    public Node getBottom() {
        return bottom;
    }

    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }

    public Button getBack() {
        return back;
    }

    public void setBack(Button back) {
        this.back = back;
    }

    public Button getExit() {
        return exit;
    }

    public void setExit(Button exit) {
        this.exit = exit;
    }


}
