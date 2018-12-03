package Maze.MazeDifficulty;

import Interface.MazeInterface;

import Maze.*;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.Models.Obstacle;
import inventory.UI.InventoryUI;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Maze implements  Runnable, MazeInterface {
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
    protected int bgem=0,ggem=0,pgem=0;
    protected Item currentItem=null;
    protected Inventory inventory;
    protected Scene scene;
    protected long timeLimit;
    protected InventoryUI inventoryMenu;
    protected ArrayList<Obstacle> currentObstacle;
    protected  ArrayList<Item>items;
    protected  boolean freezeTime=false;
    protected  long freezeLength=1;
    protected Label invLabel;
    protected boolean gameOver=false;
    protected Label roomLbl=new Label();
    private AnimationTimer timer;
    protected  boolean dead=false;

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getBgem() {
        return bgem;
    }

    public void setBgem(int bgem) {
        this.bgem = bgem;
    }

    public int getGgem() {
        return ggem;
    }

    public void setGgem(int ggem) {
        this.ggem = ggem;
    }

    public int getPgem() {
        return pgem;
    }

    public void setPgem(int pgem) {
        this.pgem = pgem;
    }

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

    public long getFreezeLength() {
        return freezeLength;
    }

    public void setFreezeLength(long freezeLength) {
        this.freezeLength = freezeLength;
    }

    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {
        timer = new AnimationTimer() {
            private long timestamp;
            private long time = 0;
            private long fraction = 0;

            @Override
            public void start() {
                // current time adjusted by remaining time from last run
                timestamp = System.currentTimeMillis() - fraction;
                super.start();
            }

            @Override
            public void stop() {
                super.stop();
                // save leftover time not handled with the last update
                fraction = System.currentTimeMillis() - timestamp;
            }

            @Override
            public void handle(long now) {
                long newTime = System.currentTimeMillis();
                if (timestamp + 1000 <= newTime) {
                    long deltaT = (newTime - timestamp) / 1000;
                    time += deltaT;
                    timestamp += 1000 * deltaT;

                }
            }
            public long getTime()
            {
                return time;
            }
            @Override
            public  String toString()
            {
                return  time+"";
            }
        };
        this.player=player;
        this.inventory=inventory;
        this.scene=scene;
        this.inventoryMenu=inventoryMenu;
        this.invLabel=invLabel;
        this.currentItem=item;
        this.items=items;
        System.out.println(">>"+invLabel.getText());
        System.out.println(inventoryMenu.getItemCount());

        //initModality(Modality.APPLICATION_MODAL);
        //Group root = new Group(startMaze());
        //Scene scene = new Scene(startMaze(), 400, 400);
        //Pane pane = new Pane();
        mazeImage= new Tile("intro.png","a");
        Image image1 = new Image(getClass().getResourceAsStream(player.getUrl()));
        playerImage.setImage(image1);//player


        //TranslateTransition transition = createTranslateTransition(selectedImage);

        //pane.getChildren().add(mazeImage);
        //Scene scene = new Scene(pane);

        BorderPane window = new BorderPane();
        //StackPane stack = new StackPane();
        window.setTop(new Label("HEY"));
        window.setCenter(stack);
        roomLbl.setFont(new Font(100));
        roomLbl.setTextFill(Color.GREY);

        stack.setAlignment(Pos.CENTER);
        stack.getChildren().addAll(mazeImage,roomLbl,playerImage);//player and current maze
    currentTile=mazeImage;
        startMaze(scene);

        setup();
        return window;
    }
    @Override
    public void resetMaze()
    {
        initMaze(scene,inventoryMenu,invLabel,currentItem,player,items,inventory);
    }
    public void startMaze(Scene scene)
    {
        playerImage =  new ImageView(new Image(getClass().getResourceAsStream("/Images/hero.png")));//player
        moveImage(scene, playerImage, transition);

    }
    public void endGame()
    {
        stack.getChildren().clear();
        stack.getChildren().add(new ImageView(new Image(getClass().getResourceAsStream("/Images/youWin.png"))));
    }
    public boolean isGameOver() {
        System.out.println("HERE");
        return gameOver;
    }

    public boolean isFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(boolean freezeTime) {
        this.freezeTime = freezeTime;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    protected abstract TranslateTransition createTranslateTransition(final ImageView image);
    public Item itemGenerator()
    {


        int rand=(int)(Math.random()*(inventory.getItems().size()));
        if(inventory.getItems().size()>=4) {

            Collections.shuffle(inventory.getItems());
            System.out.println(inventory.getItems().get(0));
            if(inventory.getItems().get(0).getID().contains("key"))
            return  itemGenerator();
            else {
                System.out.println("----------------------------------------");
                return inventory.getItems().get(0);
            }
        }else
            return itemGenerator();
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
                        case UP: {
                            if (currentTile.getTileType() != TILETYPE.T2 && currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T7&&currentTile.getTileType()!=TILETYPE.T9&&currentTile.getTileType()!=TILETYPE.T8&&currentTile.getTileType()!=TILETYPE.T10&&currentTile.getTileType()!=TILETYPE.T14&&currentTile.getTileType()!=TILETYPE.BLOCKED&&currentTile.getTileType()!=TILETYPE.GOAL) {
                                transition.setToY(image.getY() - (KEYBOARD_MOVEMENT_DELTA - 320));
                                transition.setDuration(new Duration(Math.abs((player.getSpeed()))));
                                canMove = false; transition.playFromStart();whatDir = "up";
                                System.out.println("Player Sppeed: "+player.getSpeed());

                             //   roomLbl.setText(currentTile.getID().toUpperCase());
                            }
                            else if(currentTile.getTileType()==TILETYPE.GOALA){canMove=false;endGame();}
                        } break;
                        case RIGHT: {
                            if (currentTile.getTileType() != TILETYPE.T1 && currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T8&&currentTile.getTileType()!=TILETYPE.T10&&currentTile.getTileType()!=TILETYPE.T11&&currentTile.getTileType()!=TILETYPE.T13&&currentTile.getTileType()!=TILETYPE.T15&& currentTile.getTileType()!=TILETYPE.BLOCKED) {
                                transition.setToX(image.getX() + (KEYBOARD_MOVEMENT_DELTA - 300));
                                transition.setDuration(new Duration(Math.abs((player.getSpeed()))));
                                canMove = false; transition.playFromStart();whatDir = "right";
                              //  roomLbl.setText(currentTile.getID().toUpperCase());
                            }
                            else if(currentTile.getTileType()==TILETYPE.GOALA){canMove=false;endGame();}
                        } break;
                        case LEFT: {
                            if (currentTile.getTileType() != TILETYPE.T3 && currentTile.getTileType() != TILETYPE.T7 && currentTile.getTileType() != TILETYPE.T8&&currentTile.getTileType()!=TILETYPE.T9&&currentTile.getTileType()!=TILETYPE.T12&&currentTile.getTileType()!=TILETYPE.T12&&currentTile.getTileType()!=TILETYPE.T13&&currentTile.getTileType()!=TILETYPE.T15&& currentTile.getTileType()!=TILETYPE.BLOCKED) {
                                transition.setToX(image.getX() - (KEYBOARD_MOVEMENT_DELTA - 300));
                                transition.setDuration(new Duration(Math.abs((player.getSpeed()))));
                                canMove = false; transition.playFromStart();whatDir = "left";
                                //roomLbl.setText(currentTile.getID().toUpperCase());
                            }
                            else if(currentTile.getTileType()==TILETYPE.GOALA){canMove=false;endGame();}
                        } break;
                        case DOWN: {
                            if (currentTile.getTileType() != TILETYPE.T5 &&currentTile.getTileType() != TILETYPE.T6 && currentTile.getTileType() != TILETYPE.T7&&currentTile.getTileType()!=TILETYPE.T12&&currentTile.getTileType()!=TILETYPE.T14&&currentTile.getTileType()!=TILETYPE.T13&&currentTile.getTileType()!=TILETYPE.T11&&currentTile.getTileType()!=TILETYPE.T14&& currentTile.getTileType()!=TILETYPE.BLOCKED&&currentTile.getTileType()!=TILETYPE.GOAL) {
                                transition.setToY(image.getY() + (KEYBOARD_MOVEMENT_DELTA - 320));
                                transition.setDuration(new Duration(Math.abs((player.getSpeed()))));
                                canMove = false; transition.playFromStart();whatDir = "down";
                               // roomLbl.setText(currentTile.getID().toUpperCase());
                            }
                            else if(currentTile.getTileType()==TILETYPE.GOALA){canMove=false;endGame();}
                        } break;
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
