package Maze.MazeDifficulty;

import inventory.Models.*;
import Maze.*;
import inventory.UI.InventoryUI;
import inventory.controls.RandomNumberGenerator;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MediumMaze extends Maze {
    private Tile a, b, c, d, e, f, g, h, i, j, k, exit, nextTile;
    private BorderPane window;
    private Button consumeButton = new Button("CONSUME");
    private AnimationTimer timer;
    private Button returnButton = new Button("RETURN");


    @Override
    public void setup() {

        title = "Intermediate maze";
        System.out.println("setting...");
        a = b = new Tile("maze4.png", "a");
        currentTile = a;
        timeLimit = 120000;

        System.out.println(invLabel.getText());

        b = new Tile("maze5.png", "b");
        c = new Tile("maze2.png", "c");
        d = new Tile("maze7.png", "d");
        e = new Tile("maze1.png", "e");
        f = new Tile("maze6.png", "f");
        g = new Tile("maze7.png", "g");
        i = new Tile("maze7.png", "h");
        j = new Tile("maze8.png", "i");
        h = new Tile("maze2.png", "j");
        k = new Tile("exitRight.png", "k");
        exit = new Tile("youWin.png", "Goal");


        a.setExits(c, d, j, b);
        b.setExits(e, a, null, f);
        c.setExits(null, g, a, e);
        d.setExits(null, null, null, a);
        e.setExits(h, c, b, null);
        f.setExits(null, b, null, null);
        g.setExits(null, null, null, c);
        i.setExits(null, null, null, h);
        h.setExits(null, i, e, k);
        j.setExits(a, null, null, null);
        k.setExits(null, h, null, exit);
        exit.setTileType(TILETYPE.GOALA);


        //load Keys
        KeyItem gkey = new KeyItem("gold_key", "Gold key", "", 50, 50, true);
        gkey.setPlayAnimation(true);
        KeyItem bkey = new KeyItem("blue_key", "Blue key", "", 50, 50, true);
        gkey.setPlayAnimation(true);
        KeyItem gnkey = new KeyItem("green_key", "Green key", "", 50, 50, true);
        gkey.setPlayAnimation(true);
        KeyItem rkey = new KeyItem("red_key", "Red key", "", 50, 50, true);
        gkey.setPlayAnimation(true);
        KeyItem mkey = new KeyItem("magenta_key", "Magenta key", "", 50, 50, true);
        gkey.setPlayAnimation(true);

        //Add items


        Door doorA = new Door("gold_key", "Gold_Door", "", "gold_door.png", 80.0, 300.0);
        Door doorA1 = new Door("gold_key", "Gold_Door", "", "gold_door.png", 80.0, 300.0);
        PositionedObstacle pObstacle = new PositionedObstacle(doorA);
        PositionedObstacle pObstacleB = new PositionedObstacle(doorA1);
        pObstacle.setCoordinates(new Coord(210, 0));
        pObstacleB.setCoordinates(new Coord(-210, 0));

        tweenDoor(a, b, doorA, doorA1, pObstacle, pObstacleB, TILETYPE.T1, TILETYPE.T12);
        //tile B


        //tileC

        Door redDoor = new Door("red_key", "Red_Door", "", "red_door.png", 80.0, 300.0);
        PositionedObstacle pObstacle2 = new PositionedObstacle(redDoor);
        pObstacle2.setCoordinates(new Coord(-210, 0));
        addDoor(redDoor, pObstacle2, c, TILETYPE.T9);
        PositionedItem it2 = new PositionedItem(itemGenerator(), new Coord((int) new RandomNumberGenerator(-150, 150).getRandomNumber('i'), (int) new RandomNumberGenerator(-150, 150).getRandomNumber('i')));


//tileD
        PositionedItem rKey = new PositionedItem(rkey);
        rKey.setCoordinates(new Coord(270, 50));
        addItem(rKey, rkey, d);

        //tileE
        Door blueDoor = new Door("blue_key", "Blue_Door", "", "blue_door.png", 300.0, 80.0);
        PositionedObstacle pObstacle3 = new PositionedObstacle(blueDoor);
        pObstacle3.setCoordinates(new Coord(0, -210));
        addDoor(blueDoor, pObstacle3, e, TILETYPE.T10);
        //tileF
        //tiltG

        PositionedItem bKey = new PositionedItem(bkey);
        bKey.setCoordinates(new Coord(270, 50));
        addItem(bKey, bkey, g);

        //tile J
        PositionedItem gKey = new PositionedItem(gkey);
        gKey.setCoordinates(new Coord(270, 50));
        addItem(gKey, gkey, j);


        //tile H
        Door doorM = new Door("magenta_key", "magenta_Door", "", "magenta_door.png", 80.0, 300.0);

        PositionedObstacle pObstacleM = new PositionedObstacle(doorM);
        pObstacleM.setCoordinates(new Coord(210, 0));
        addDoor(doorM, pObstacleM, h, TILETYPE.T10);
        //tile I
        PositionedItem mKey = new PositionedItem(mkey);
        mKey.setCoordinates(new Coord(-270, 50));
        addItem(mKey, mkey, i);

        //Random Items
        ArrayList<Item> randomItems = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            randomItems.add(itemGenerator());
        ArrayList<PositionedItem> pRitems = new ArrayList<PositionedItem>();
        for (int i = 0; i < randomItems.size(); i++) {
            pRitems.add(new PositionedItem(randomItems.get(i)));
            pRitems.get(i).setCoordinates(new Coord(200 + (i * 10), 60));
        }
        for (int x = 0; x < pRitems.size(); x++) {
            System.out.println("Gems " + pRitems.get(x).getItem().getID());
            int num = (int) (Math.random() * (12 - 1) + 1);
            switch (num) {
                case 1:
                    addItem(pRitems.get(x), randomItems.get(x), a);
                    break;
                case 2:
                    addItem(pRitems.get(x), randomItems.get(x), b);
                    break;
                case 3:
                    addItem(pRitems.get(x), randomItems.get(x), c);
                    break;
                case 4:
                    addItem(pRitems.get(x), randomItems.get(x), d);
                    break;
                case 5:
                    addItem(pRitems.get(x), randomItems.get(x), e);
                    break;
                case 6:
                    addItem(pRitems.get(x), randomItems.get(x), f);
                    break;
                case 7:
                    addItem(pRitems.get(x), randomItems.get(x), g);
                    break;
                case 8:
                    addItem(pRitems.get(x), randomItems.get(x), h);
                    break;
                case 9:
                    addItem(pRitems.get(x), randomItems.get(x), i);
                    break;
                case 10:
                    addItem(pRitems.get(x), randomItems.get(x), j);
                    break;
                case 11:
                    addItem(pRitems.get(x), randomItems.get(x), k);
                    break;
                default:
                    addItem(pRitems.get(x), randomItems.get(x), j);
                    break;


            }
        }


        //for item hunt
        ArrayList<Gem> gems = inventory.getGems();

        ArrayList<PositionedItem> pgems = new ArrayList<PositionedItem>();
        for (int i = 0; i < gems.size(); i++)

            pgems.add(new PositionedItem(gems.get(i), new Coord((int) new RandomNumberGenerator(-150, 150).getRandomNumber('i'), (int) new RandomNumberGenerator(-150, 150).getRandomNumber('i'))));

        for (int n = 0; n < pgems.size(); n++) {
            System.out.println("Gems " + pgems.get(n).getItem().getID());
            int num = (int) (Math.random() * (10 - 1) + 1);
            switch (num) {
                case 1:
                    addGem(pgems.get(n), gems.get(n), a);
                    break;
                case 2:
                    addGem(pgems.get(n), gems.get(n), b);
                    break;
                case 3:
                    addGem(pgems.get(n), gems.get(n), c);
                    break;
                case 4:
                    addGem(pgems.get(n), gems.get(n), d);
                    break;
                case 5:
                    addGem(pgems.get(n), gems.get(n), e);
                    break;
                case 6:
                    addGem(pgems.get(n), gems.get(n), f);
                    break;
                case 7:
                    addGem(pgems.get(n), gems.get(n), g);
                    break;
                case 8:
                    addGem(pgems.get(n), gems.get(n), h);
                    break;
                case 9:
                    addGem(pgems.get(n), gems.get(n), i);
                    break;
                case 10:
                    addGem(pgems.get(n), gems.get(n), j);
                    break;


            }
        }


    }

    @Override
    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {

        System.out.println("INITIALIZING MASE AT SIMPLE ");
        window = super.initMaze(scene, inventoryMenu, invLabel, item, player, items, inventory);

        consumeButton.setDisable(true);

        returnButton.setDisable(true);

        consumeButton.setOnAction(
                e -> {
                    if (currentItem != null) {
                        switch (currentItem.getType().toUpperCase()) {
                            case "HP": {
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);
                                player.setHealth(player.getHealth() + (int) currentItem.use());
                            }
                            break;
                            case "SP": {
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);

                                player.setSpeed(player.getSpeed() / 2);

                            }
                            break;

                            case "TIME": {
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);
                                freezeTime = true;
                                time = (long) currentItem.use();
                                freezeLength = time;


                            }
                            break;
                            default:
                                break;
                        }


                    }
                });
        returnButton.setOnAction(
                e -> {
                    if (currentItem != null)
                        scene.setCursor(Cursor.DEFAULT);
                    currentItem = null;

                });
        HBox hbox = new HBox();
        hbox.getChildren().addAll(consumeButton, returnButton);
        hbox.setPadding(new Insets(15.0));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(25);
        window.setTop(hbox);
        return window;
    }

    @Override
    public boolean hasStarted() {
        return !gameOver;
    }

    @Override
    public void setStop(boolean thread) {
        gameOver = !thread;

    }

    private void addItem(PositionedItem item, Item p, Tile t) {

        t.addItem(item);


        p.setOnAction(e ->
        {

            t.removeItem(item);
            invLabel.setText(inventoryMenu.getItemCount() + 1 + "");


            if (!inventoryMenu.isFull()) {
                p.saveItem();

                currentItem = inventoryMenu.getItem();

            } else {


            }
        });


    }

    private void addGem(PositionedItem item, Gem g, Tile t) {
        t.addItem(item);


        g.setOnAction(e ->
        {

            t.removeItem(item);

            switch (g.getID().toLowerCase()) {
                case "gem":
                    pgem++;
                    break;
                case "bgem":
                    bgem++;
                    break;
                case "ggem":
                    ggem++;
                    break;
                default:
                    break;

            }
        });


    }

    private void tweenDoor(Tile A, Tile B, Door door, Door doorB, PositionedObstacle obs, PositionedObstacle obs2, TILETYPE newTA, TILETYPE newTB) {
        door.setOnAction(e -> {

            if (currentItem != null) {
                if (currentItem.getType().equalsIgnoreCase("KEY"))
                    if (door.openDoor((KeyItem) currentItem)) {
                        scene.setCursor(Cursor.DEFAULT);
                        inventoryMenu.dropItem(currentItem);
                        Animation anim = door.open(1000);
                        anim.play();
                        anim.onFinishedProperty().set(after -> {
                            A.removeObstacle(obs);
                            B.removeObstacle(obs2);


                        });
                    } else {
                        System.out.println("Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });
        doorB.setOnAction(e -> {

            if (currentItem != null) {
                if (currentItem.getType().equalsIgnoreCase("KEY"))
                    if (doorB.openDoor((KeyItem) currentItem)) {
                        scene.setCursor(Cursor.DEFAULT);
                        inventoryMenu.dropItem(currentItem);
                        Animation anim = doorB.open(1000);
                        anim.play();
                        anim.onFinishedProperty().set(after -> {
                            A.removeObstacle(obs);
                            B.removeObstacle(obs2);


                        });
                    } else {
                        System.out.println("Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });

        A.addObstacle(obs, newTA);
        B.addObstacle(obs2, newTB);

    }

    private void addDoor(Door door, PositionedObstacle obs, Tile t, TILETYPE newT) {
        door.setOnAction(e -> {

            if (currentItem != null) {
                if (currentItem.getType().equalsIgnoreCase("KEY"))
                    if (door.openDoor((KeyItem) currentItem)) {
                        scene.setCursor(Cursor.DEFAULT);
                        inventoryMenu.dropItem(currentItem);
                        Animation anim = door.open(1000);
                        anim.play();
                        anim.onFinishedProperty().set(after -> {
                            t.removeObstacle(obs);


                        });
                    } else {
                        System.out.println("Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });

        t.addObstacle(obs, newT);
    }

    @Override
    protected TranslateTransition createTranslateTransition(final ImageView playerImage) {
        TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, playerImage);
        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                playerImage.setX(playerImage.getTranslateX() + playerImage.getX());
                playerImage.setY(playerImage.getTranslateY() + playerImage.getY());
                playerImage.setTranslateX(0);
                playerImage.setTranslateY(0);
                transition.setToX(0);
                transition.setToY(0);
                Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));


                if (whatDir.equals("right")) {
                    nextTile = currentTile.nextTile("west");
                    if (nextTile != null) {
                        canMove = true;
                        currentTile = nextTile;
                    } else canMove = false;



                } else if (whatDir.equals("left")) {
                    nextTile = currentTile.nextTile("east");
                    if (nextTile != null) {
                        canMove = true;
                        currentTile = nextTile;
                    } else canMove = false;



                } else if (whatDir.equals("up")) {
                    nextTile = currentTile.nextTile("north");
                    if (nextTile != null) {
                        canMove = true;
                        currentTile = nextTile;
                    } else canMove = false;



                } else if (whatDir.equals("down")) {
                    nextTile = currentTile.nextTile("south");
                    if (nextTile != null) {
                        canMove = true;
                        currentTile = nextTile;
                    } else canMove = false;


                }


                stack.getChildren().setAll(currentTile, roomLbl, playerImage);


                canMove = true;
            }
        });
        return transition;
    }


    private long time = 0;

    @Override
    public void run() {

        while (!gameOver) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    roomLbl.setText(currentTile.getID().toUpperCase());
                }
            });
            if (freezeTime) {
                if (time > 0)
                    time -= 1000;

                freezeLength = time;
                Label timeLabel = new Label();


                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        window.setLeft(timeLabel);
                        timeLabel.setFont(new Font(72));
                        timeLabel.setTextFill(Color.WHITE);
                        if (time < 5000)
                            timeLabel.setTextFill(Color.RED);
                        if (time == 0)
                            timeLabel.setText("");

                        else timeLabel.setText(new SimpleDateFormat("ss").format(time));

                    }
                });
                if (time <= 0) {
                    freezeTime = false;

                }

            }

            if (currentItem != null) {
                returnButton.setDisable(false);
                consumeButton.setDisable(false);
            }


            if (inventoryMenu != null)
                currentItem = inventoryMenu.getItem();
            if (currentTile != null) {

                if (currentTile.getTileType() == TILETYPE.GOALA)
                    gameOver = true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                System.out.println("Thread Interrupted");
                gameOver = true;
            }
        }
    }
}
