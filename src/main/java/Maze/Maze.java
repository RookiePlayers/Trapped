package Maze;

import inventory.Models.HealthPotion;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.Models.Obstacle;
import inventory.UI.InventoryUI;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Maze implements  Runnable{
    protected Player player;
    protected String title;
    protected static final int KEYBOARD_MOVEMENT_DELTA = 700;
    protected static final Duration TRANSLATE_DURATION = Duration.seconds(2.5);
    protected boolean canMove = true;
    protected String whatDir = "";
    protected Tile mazeImage,currentTile;
    protected ImageView playerImage = new ImageView(new Image(getClass().getResourceAsStream("/Images/hero.png")));
    protected StackPane stack = new StackPane();
    private final TranslateTransition transition = createTranslateTransition(playerImage);
    private int currentMaze = 0;
    private String whereInMaze = "a";
    private int n = 4;
    protected Item currentItem=null;
    protected Inventory inventory;
    protected Scene scene;
    protected InventoryUI inventoryMenu;
    protected ArrayList<Obstacle> currentObstacle;
    protected Label invLabel;

    public abstract void  setup();
    public void setInventoryMenu(InventoryUI InventorMenu)
    {
        this.inventoryMenu=inventoryMenu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu,Label invLabel,Item item,Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {
        this.player=player;
        this.inventory=inventory;
        this.scene=scene;
        this.inventoryMenu=inventoryMenu;
        this.invLabel=invLabel;
        this.currentItem=item;
        System.out.println(">>"+invLabel.getText());
        System.out.println(inventoryMenu.getItemCount());

        //initModality(Modality.APPLICATION_MODAL);
        //Group root = new Group(startMaze());
        //Scene scene = new Scene(startMaze(), 400, 400);
        //Pane pane = new Pane();
        mazeImage= new Tile("maze4.png","a");
        Image image1 = new Image(getClass().getResourceAsStream(player.getUrl()));
        playerImage.setImage(image1);//player


        //TranslateTransition transition = createTranslateTransition(selectedImage);

        //pane.getChildren().add(mazeImage);
        //Scene scene = new Scene(pane);

        BorderPane window = new BorderPane();
        //StackPane stack = new StackPane();
        window.setCenter(stack);

        stack.setAlignment(Pos.CENTER);
        stack.getChildren().addAll(mazeImage, playerImage);//player and current maze
    currentTile=mazeImage;
        startMaze(scene);

        setup();
        return window;
    }
    public void startMaze(Scene scene)
    {



        Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
        //int n = (int) (Math.random()*5)+1;

        if (n == 1)
        {
            image2 = new Image(getClass().getResourceAsStream("/Images/maze1.png"));
            currentMaze = 1;
        }
        if (n == 2)
        {
            image2 = new Image(getClass().getResourceAsStream("/Images/maze2.png"));
            currentMaze = 2;
        }
        if (n == 3)
        {
            image2 = new Image(getClass().getResourceAsStream("/Images/maze3.png"));
            currentMaze = 3;
        }
        if (n == 4)
        {
            image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
            currentMaze = 4;
        }
        if (n == 5)
        {
            image2 = new Image(getClass().getResourceAsStream("/Images/maze5.png"));
            currentMaze = 5;
        }



        playerImage =  new ImageView(new Image(getClass().getResourceAsStream("/Images/hero.png")));//player
        moveImage(scene, playerImage, transition);

    }
    protected abstract TranslateTransition createTranslateTransition(final ImageView image);
    protected Item itemGenerator()
    {
        for(Item item:inventory.getItems())
        {
            System.out.println("Item: "+item.getName());
        }
        int rand=(int)(Math.random()*(inventory.getItems().size()));
        if(inventory.getItems().size()>1)
            return inventory.getItems().get(rand);
        else
            return new HealthPotion();
    }
    private void moveImage(Scene scene, final ImageView image, TranslateTransition transition)
    {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent event)
            {
                System.out.println("Movabe? "+canMove+"\nTILETYPE: "+currentTile.getTileType());
                if (canMove)
                {
                    switch (event.getCode())
                    {
                        case UP: if (currentTile.getTileType() != TILETYPE.T2 && currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T7&&currentTile.getTileType()!=TILETYPE.T9&&currentTile.getTileType()!=TILETYPE.BLOCKED) {transition.setToY(image.getY() - (KEYBOARD_MOVEMENT_DELTA - 320)); canMove = false; transition.playFromStart();whatDir = "up";} break;
                        case RIGHT: if (currentTile.getTileType() != TILETYPE.T1 && currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T8&& currentTile.getTileType()!=TILETYPE.BLOCKED) {transition.setToX(image.getX() + (KEYBOARD_MOVEMENT_DELTA - 300)); canMove = false; transition.playFromStart();whatDir = "right";} break;
                        case LEFT: if (currentTile.getTileType() != TILETYPE.T3 && currentTile.getTileType() != TILETYPE.T7 && currentTile.getTileType() != TILETYPE.T8&&currentTile.getTileType()!=TILETYPE.T9&& currentTile.getTileType()!=TILETYPE.BLOCKED) {transition.setToX(image.getX() - (KEYBOARD_MOVEMENT_DELTA - 300)); canMove = false; transition.playFromStart();whatDir = "left";} break;
                        case DOWN: if (currentTile.getTileType() != TILETYPE.T5 &&currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T7 && currentTile.getTileType() != TILETYPE.T8&& currentTile.getTileType()!=TILETYPE.BLOCKED) {transition.setToY(image.getY() + (KEYBOARD_MOVEMENT_DELTA - 320)); canMove = false; transition.playFromStart();whatDir = "down";} break;
                    }
                }
            }
        });
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
