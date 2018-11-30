package Maze;

import Interface.MazeInterface;
import inventory.Models.Door;
import inventory.Models.KeyItem;
import inventory.UI.InventoryUI;
import inventory.controls.RandomNumberGenerator;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class SimpleMaze extends Maze {
private Tile a,b,c,d,e,f,g,h,i,j,k,exit,nextTile;


    @Override
public void setup()
{
    title="Simple maze";
    System.out.println("setting...");
    a=mazeImage;
    currentTile=a;

    System.out.println(invLabel.getText());

    b=new Tile("maze5.png","b");
    c=new Tile("maze2.png","c");
    d=new Tile("maze7.png","d");
    e=new Tile("maze1.png","e");
    f=new Tile("maze8.png","f");
    g=new Tile("maze7.png","g");
    i=new Tile("maze7.png","h");
    j=new Tile("maze8.png","i");
    h=new Tile("maze2.png","j");
    k=new Tile("exitRight.png","k");
    exit=new Tile("youWin.png","Goal");


    a.setExits(c,d,j,b);
    b.setExits(e,a,null,f);
    c.setExits(null,g,a,e);
    d.setExits(null,null,null,a);
    e.setExits(h,c,b,null);
    f.setExits(null,b,null,null);
    g.setExits(null,null,null,c);
    i.setExits(null,null,null,h);
    h.setExits(null,i,e,k);
    j.setExits(a,null,null,null);
    k.setExits(null,h,null,exit);
    exit.setTileType(TILETYPE.GOALA);

    //Add items

    PositionedItem pItem=new PositionedItem(itemGenerator(),new Coord((int)new RandomNumberGenerator(20,250).getRandomNumber('i'),(int)new RandomNumberGenerator(20,250).getRandomNumber('i')));
    a.addItem(pItem);
    for(PositionedItem p:a.getItemsInRoom())
    {

        p.getItem().setOnAction(e->
        {
            System.out.println("clicked***");
            a.removeItem(p);
          invLabel.setText(inventoryMenu.getItemCount() + 1 + "");

            // it.setPlayAnimation(false);
            if (!inventoryMenu.isFull()) {
                p.getItem().saveItem();
           //     inventoryMenu = new InventoryUI(scene, 4);
                currentItem=inventoryMenu.getItem();

            } else {


            }
        });
    }



    Door doorA=new Door("gold_key", "Gold_Door", "", "gold_door.png",80.0, 300.0);
    PositionedObstacle pObstacle=new PositionedObstacle(doorA);
    doorA.setOnAction(e -> {
        System.out.println("Current Item: "+currentItem);
        if (currentItem!=null) {
            if (currentItem.getType().equalsIgnoreCase("KEY"))
                if (doorA.openDoor((KeyItem) currentItem)) {
                    scene.setCursor(Cursor.DEFAULT);
                    inventoryMenu.dropItem(currentItem);
                    Animation anim = doorA.open(1000);
                    anim.play();
                    anim.onFinishedProperty().set(after -> {
                       a.removeObstacle(pObstacle);


                    });
                } else {
                    System.out.println( "Invalid Target\nUse Item: " + doorA.getID());
                }
        }
    });
    pObstacle.setCoordinates(new Coord(210,0));
    a.addObstacle(pObstacle,TILETYPE.T1);
    //tileC

    Door redDoor=new Door("red_key", "Red_Door", "", "red_door.png",80.0, 300.0);
    PositionedObstacle pObstacle2=new PositionedObstacle(redDoor);
    redDoor.setOnAction(e -> {
        System.out.println("Current Item: "+currentItem);
        if (currentItem!=null) {
            if (currentItem.getType().equalsIgnoreCase("KEY"))
                if (redDoor.openDoor((KeyItem) currentItem)) {
                    scene.setCursor(Cursor.DEFAULT);
                    inventoryMenu.dropItem(currentItem);
                    Animation anim = redDoor.open(1000);
                    anim.play();
                    anim.onFinishedProperty().set(after -> {
                        c.removeObstacle(pObstacle2);


                    });
                } else {
                    System.out.println( "Invalid Target\nUse Item: " + redDoor.getID());
                }
        }
    });
    pObstacle2.setCoordinates(new Coord(-210,0));
    c.addObstacle(pObstacle2,TILETYPE.T9);
//tileD
    PositionedItem redKey=new PositionedItem(inventory.getItems().get(1),new Coord((int)new RandomNumberGenerator(20,250).getRandomNumber('i'),(int)new RandomNumberGenerator(20,250).getRandomNumber('i')));
    d.addItem(redKey);
    for(PositionedItem p:d.getItemsInRoom())
    {

        p.getItem().setOnAction(e->
        {
            System.out.println("clicked***");
            d.removeItem(p);
            invLabel.setText(inventoryMenu.getItemCount() + 1 + "");

            // it.setPlayAnimation(false);
            if (!inventoryMenu.isFull()) {
                p.getItem().saveItem();
               // inventoryMenu = new InventoryUI(scene, 4);
                currentItem=inventoryMenu.getItem();

            } else {


            }
        });
    }
    //tileE
    //tiltG
    PositionedItem goldKey=new PositionedItem(inventory.getItems().get(0),new Coord((int)new RandomNumberGenerator(20,250).getRandomNumber('i'),(int)new RandomNumberGenerator(20,250).getRandomNumber('i')));
    g.addItem(goldKey);

    for(PositionedItem p:c.getItemsInRoom())
    {

        p.getItem().setOnAction(e->
        {
            System.out.println("clicked***");
            g.removeItem(p);
            invLabel.setText(inventoryMenu.getItemCount() + 1 + "");

            // it.setPlayAnimation(false);
            if (!inventoryMenu.isFull()) {
                p.getItem().saveItem();
                //  inventoryMenu = new InventoryUI(scene, 4);
                currentItem=inventoryMenu.getItem();

            } else {


            }
        });
    }





}

    @Override
    protected TranslateTransition createTranslateTransition(final ImageView playerImage) {
        TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, playerImage);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent t) {
                playerImage.setX(playerImage.getTranslateX() + playerImage.getX());
                playerImage.setY(playerImage.getTranslateY() + playerImage.getY());
                playerImage.setTranslateX(0);
                playerImage.setTranslateY(0);
                transition.setToX(0);
                transition.setToY(0);
                Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
                //int n = (int) (Math.random()*5)+1;
                System.out.println(currentTile.getTileType());
                if(whatDir.equals("right"))
                {
                    nextTile=currentTile.nextTile("west");
                    if(nextTile!=null){
                        canMove = true;
                        currentTile=nextTile;
                    }
                    else canMove=false;

                    System.out.println("Can Player move: "+canMove);

                }
                else
                if(whatDir.equals("left"))
                {
                    nextTile=currentTile.nextTile("east");
                    if(nextTile!=null)
                    {
                        canMove = true;
                        currentTile=nextTile;
                    }
                    else canMove=false;

                    System.out.println("Can Player move: "+canMove);

                }
                else
                if(whatDir.equals("up"))
                {
                    nextTile=currentTile.nextTile("north");
                    if(nextTile!=null){
                        canMove = true;
                        currentTile=nextTile;
                    }
                    else canMove=false;

                    System.out.println("Can Player move: "+canMove);

                }
                else
                if(whatDir.equals("down"))
                {
                    nextTile=currentTile.nextTile("south");
                    if(nextTile!=null){
                        canMove = true;
                        currentTile=nextTile;
                    }
                    else canMove=false;
                    System.out.println("Can Player move: "+canMove);

                }





                stack.getChildren().setAll(currentTile,playerImage);

               /* if (n == 100)
                {
                    stack.getChildren().clear();
                    mazeImage.setImage(image2);
                    stack.getChildren().addAll(mazeImage);
                    //selectedImage.setImage(image1);
                    //selectedImage.setX(200);
                    //selectedImage.setY(275);
                    canMove = false;
                }*/
               canMove=true;
            }
        });
        return transition;
    }


    @Override
    public void run() {
        while(!gameOver)
        {

              System.out.println("running....");
            if(inventoryMenu!=null)
                currentItem=inventoryMenu.getItem();
            if(currentTile!=null)
            {
                System.out.println(currentTile.getTileType());
                if(currentTile.getTileType()==TILETYPE.GOALA)
                gameOver=true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
