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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HardMaze extends Maze {
    private Tile a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,exit,nextTile;
    private BorderPane window;
    private Button consumeButton=new Button("CONSUME");
    private AnimationTimer timer;
    private Button returnButton=new Button("RETURN");
    private HBox heartBox=new HBox();


    @Override
    public void setup()
    {



        System.out.println(invLabel.getText());

        a=new Tile("maze5.png","a");
        b=new Tile("maze9.png","b");
        c=new Tile("maze5.png","c");
        d=new Tile("maze5.png","d");
        e=new Tile("maze11.png","e");
        f=new Tile("maze1.png","f");
        g=new Tile("maze4.png","g");
        h=new Tile("maze4.png","h");
        i=new Tile("maze4.png","i");
        j=new Tile("maze2.png","j");

        k=new Tile("maze7.png","k");
        l=new Tile("maze12.png","l");
        m=new Tile("maze4.png","m");
        n=new Tile("maze2.png","n");
        o=new Tile("maze3.png","o");
        p=new Tile("maze8B.png","p");
        q=new Tile("maze12.png","q");
        r=new Tile("maze13.png","r");
        s=new Tile("exitLeft.png","goal");

        exit=new Tile("youWin.png","Goal");

        title="Hard maze";
        System.out.println("setting...");

        currentTile=a;
        timeLimit=120000;
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

        //NESW
        a.setExits(i,b,null,c);
        b.setExits(j,null,null,a);
        c.setExits(h,a,null,d);
        d.setExits(g,c,null,e);
        e.setExits(f,d,null,null);
        f.setExits(l,g,e,null);
        g.setExits(m,h,d,f);
        h.setExits(n,i,c,g);
        i.setExits(o,j,a,h);
        j.setExits(null,k,b,i);
        k.setExits(null,null,null,j);
        l.setExits(null,m,f,null);
        m.setExits(p,n,g,l);
        n.setExits(null,o,h,m);
        o.setExits(q,null,i,n);
        p.setExits(null,null,m,null);
        q.setExits(null,r,o,null);
        r.setExits(null,s,null,q);
        s.setExits(null,exit,null,r);

        exit.setTileType(TILETYPE.GOALA);


        //ADD Doors

        Door mDa=new Door("magenta_key", "Magenta_Door", "", "magenta_door.png",300.0, 80.0);
        Door mDb=new Door("magenta_key", "Magenta_Door", "", "magenta_door.png",80.0, 300.0);
        PositionedObstacle pObstacleMD=new PositionedObstacle(mDa);
        PositionedObstacle pObstacleMD1=new PositionedObstacle(mDb);
        pObstacleMD.setCoordinates(new Coord(0,-210));
        pObstacleMD1.setCoordinates(new Coord(210,0));
        tweenDoor(e,g,mDa,mDb,pObstacleMD,pObstacleMD1, TILETYPE.T6, TILETYPE.T1);

        Door rDa=new Door("red_key", "Red_Door", "", "red_door.png",300.0, 80.0);
        Door rDb=new Door("red_key", "red_Door", "", "red_door.png",80.0, 300.0);
        PositionedObstacle pObstacleRD=new PositionedObstacle(rDa);
        PositionedObstacle pObstacleRD1=new PositionedObstacle(rDb);
        pObstacleRD.setCoordinates(new Coord(0,-210));
        pObstacleRD1.setCoordinates(new Coord(210,0));
        tweenDoor(f,m,rDa,rDb,pObstacleRD,pObstacleRD1, TILETYPE.T6, TILETYPE.T1);

        Door gDa=new Door("gold_key", "gold_Door", "", "gold_door.png",80.0, 300.0);
        Door gDb=new Door("gold_key", "gold_Door", "", "gold_door.png",300.0, 80.0);
        PositionedObstacle pObstacleGD=new PositionedObstacle(gDa);
        PositionedObstacle pObstacleGD1=new PositionedObstacle(gDb);
        pObstacleGD.setCoordinates(new Coord(-210,0));
        pObstacleGD1.setCoordinates(new Coord(0,-210));
        tweenDoor(j,b,gDa,gDb,pObstacleGD,pObstacleGD1, TILETYPE.T7, TILETYPE.T7);


        Door bDa=new Door("blue_key", "blue_Door", "", "blue_door.png",80.0, 300.0);
      PositionedObstacle pObstacleBD=new PositionedObstacle(bDa);
      pObstacleBD.setCoordinates(new Coord(-210,0));
       addDoor(bDa,pObstacleBD,q,TILETYPE.T8);




        //Add items


        //tile B
        PositionedItem mKey=new PositionedItem(mkey);
        mKey.setCoordinates(new Coord(-270,50));
        addItem(mKey,mkey,b);

        TrapBreaker tbr= new TrapBreaker("tbr", "tbr", "",100, 100, true, 0);
        PositionedItem ptbr=new PositionedItem(tbr);
        ptbr.setCoordinates(new Coord(-350,100));
        addItem(ptbr,tbr,b);
        //tileC
        Trap trap1=new Trap("trap1", "trap", "", "trap1.png",100.0, 100.0);
        PositionedObstacle pObstacle=new PositionedObstacle(trap1);
        pObstacle.setCoordinates(new Coord(210,0));
        addTrap(pObstacle,trap1,a,b);
        //tileD
        Trap trap2=new Trap("trap1", "trap", "", "trap1.png",100.0, 100.0);
        PositionedObstacle pObstacle2=new PositionedObstacle(trap2);
        pObstacle2.setCoordinates(new Coord(0,-210));
        addTrap(pObstacle2,trap2,m,p);



//tileD

        //tileE
        PositionedItem rKey=new PositionedItem(rkey);
        rKey.setCoordinates(new Coord(270,50));
        addItem(rKey,rkey,e);

        //tileF
        Trap trap3=new Trap("trap1", "trap", "", "trap1.png",100.0, 100.0);
        PositionedObstacle pObstacle3=new PositionedObstacle(trap3);
        pObstacle.setCoordinates(new Coord(0,0));
        addTrap(pObstacle3,trap3,f,g);

        //tiltG


        //tile J


        //tile H

        //tile I
        Trap trap4=new Trap("trap1", "trap", "", "trap1.png",100.0, 100.0);
        PositionedObstacle pObstacle4=new PositionedObstacle(trap4);
        pObstacle4.setCoordinates(new Coord(-210,0));
        addTrap(pObstacle4,trap4,i,j);

        Trap trap5=new Trap("trap1", "trap", "", "trap1.png",100.0, 100.0);
        PositionedObstacle pObstacle5=new PositionedObstacle(trap5);
        pObstacle5.setCoordinates(new Coord(0,-210));
        addTrap(pObstacle5,trap5,i,o);


        //tile K
        HealthPotion hp=new HealthPotion("healthpotion", "+1 HP", "Adds 50 HP", 50, 50, true, 1);
        PositionedItem php=new PositionedItem(hp);
        php.setCoordinates(new Coord(270,50));
        addItem(php,hp,k);

        TimeFreeze tf=new TimeFreeze("timefreeze", "Time Freeze 10 secs", "Stops time", 50, 50, true, 10000);
        PositionedItem ptf=new PositionedItem(tf);
        ptf.setCoordinates(new Coord(250,-50));
        addItem(ptf,tf,k);

        TrapBreaker tbr2= new TrapBreaker("tbr", "Trap breaker", "",100, 100, true, 0);
        PositionedItem ptbr2=new PositionedItem(tbr2);
        ptbr2.setCoordinates(new Coord(-250,100));
        addItem(ptbr2,tbr2,k);

        //tile L

        PositionedItem bKey=new PositionedItem(bkey);
        bKey.setCoordinates(new Coord(270,50));
        addItem(bKey,bkey,l);

        //tile O
        PositionedItem gKey=new PositionedItem(gkey);
        gKey.setCoordinates(new Coord(270,50));
        addItem(gKey,gkey,o);

        // tile P
        HealthPotion hp2=new HealthPotion("healthpotion", "+1 HP", "Adds 50 HP", 50, 50, true, 2);
        PositionedItem php2=new PositionedItem(hp2);
        php2.setCoordinates(new Coord(270,50));
        addItem(php2,hp2,p);

        SpeedPotion sp=new SpeedPotion("speedpotion", "Go Faster", "increase speed", 50, 50, true, 2);
        PositionedItem psp=new PositionedItem(sp);
        ptf.setCoordinates(new Coord(250,-50));
        addItem(psp,sp,p);

        TrapBreaker tbr3= new TrapBreaker("tbr", "Trap breaker", "",100, 100, true, 0);
        PositionedItem ptbr3=new PositionedItem(tbr3);
        ptbr3.setCoordinates(new Coord(-250,100));
        addItem(ptbr3,tbr3,p);

        //Random Items
        ArrayList<Item>randomItems=new ArrayList<>();
        for(int i=0;i<10;i++)
            randomItems.add(itemGenerator());
        ArrayList<PositionedItem>pRitems=new ArrayList<PositionedItem>();
        for(int i=0;i<randomItems.size();i++)
        {
            pRitems.add(new PositionedItem(randomItems.get(i)));
            pRitems.get(i).setCoordinates(new Coord(200+(i*10),60));
        }
        for(int x=0;x<pRitems.size();x++)
        {
            System.out.println("Gems "+pRitems.get(x).getItem().getID());
            int num=(int)(Math.random()*(24-1)+1);
            switch(num)
            {
                case 1: addItem(pRitems.get(x),randomItems.get(x),a);break;
                case 2: addItem(pRitems.get(x),randomItems.get(x),b);break;
                case 3: addItem(pRitems.get(x),randomItems.get(x),c);break;
                case 4: addItem(pRitems.get(x),randomItems.get(x),d);break;
                case 5: addItem(pRitems.get(x),randomItems.get(x),e);break;
                case 6: addItem(pRitems.get(x),randomItems.get(x),f);break;
                case 7: addItem(pRitems.get(x),randomItems.get(x),g);break;
                case 8: addItem(pRitems.get(x),randomItems.get(x),h);break;
                case 9: addItem(pRitems.get(x),randomItems.get(x),i);break;
                case 10: addItem(pRitems.get(x),randomItems.get(x),j);break;
                case 11: addItem(pRitems.get(x),randomItems.get(x),k);break;
                case 12: addItem(pRitems.get(x),randomItems.get(x),l);break;
                case 13: addItem(pRitems.get(x),randomItems.get(x),m);break;
                case 14: addItem(pRitems.get(x),randomItems.get(x),n);break;
                case 15: addItem(pRitems.get(x),randomItems.get(x),o);break;
                case 16: addItem(pRitems.get(x),randomItems.get(x),p);break;
                case 17: addItem(pRitems.get(x),randomItems.get(x),q);break;
                case 18: addItem(pRitems.get(x),randomItems.get(x),r);break;
                case 19: addItem(pRitems.get(x),randomItems.get(x),s);break;
                case 20: addItem(pRitems.get(x),randomItems.get(x),j);break;


            }
        }



        ArrayList<Gem>gems=inventory.getGems();
        System.out.println("PGEM SIZE: "+gems.size());

        ArrayList<PositionedItem>pgems=new ArrayList<PositionedItem>();
        for(int i=0;i<gems.size();i++) {
            pgems.add(new PositionedItem(gems.get(i),new Coord((int)new RandomNumberGenerator(-150,150).getRandomNumber('i'),(int)new RandomNumberGenerator(-150,150).getRandomNumber('i'))));

        }

        for(int x=0;x<pgems.size();x++)
        {

            int num=(int)(Math.random()*(24-1)+1);System.out.println(num);
            switch(num)
            {

                case 1: addGem(pgems.get(x),gems.get(x),a);break;
                case 2: addGem(pgems.get(x),gems.get(x),b);break;
                case 3: addGem(pgems.get(x),gems.get(x),c);break;
                case 4: addGem(pgems.get(x),gems.get(x),d);break;
                case 5: addGem(pgems.get(x),gems.get(x),e);break;
                case 6: addGem(pgems.get(x),gems.get(x),f);break;
                case 7: addGem(pgems.get(x),gems.get(x),g);break;
                case 8: addGem(pgems.get(x),gems.get(x),h);break;
                case 9: addGem(pgems.get(x),gems.get(x),i);break;
                case 10: addGem(pgems.get(x),gems.get(x),j);break;
                case 11: addGem(pgems.get(x),gems.get(x),k);break;
                case 12: addGem(pgems.get(x),gems.get(x),l);break;
                case 13: addGem(pgems.get(x),gems.get(x),m);break;
                case 14: addGem(pgems.get(x),gems.get(x),n);break;
                case 15: addGem(pgems.get(x),gems.get(x),o);break;
                case 16: addGem(pgems.get(x),gems.get(x),p);break;
                case 17: addGem(pgems.get(x),gems.get(x),q);break;
                case 18: addGem(pgems.get(x),gems.get(x),r);break;
                case 19: addGem(pgems.get(x),gems.get(x),s);break;
                case 20: addGem(pgems.get(x),gems.get(x),j);break;
                default:addGem(pgems.get(x),gems.get(x),b);break;


            }
        }






    }
    @Override
    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {


        System.out.println("INITIALIZING MASE AT SIMPLE ");
        window=super.initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);
        heartBox.setSpacing(5);
        heartBox.setPadding(new Insets(12));

        consumeButton.setDisable(true);

        returnButton.setDisable(true);

        consumeButton.setOnAction(
                e->{
                    if (currentItem!=null) {
                        switch(currentItem.getType().toUpperCase()) {
                            case "HP": {
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);
                                player.setHealth(player.getHealth() + (int) currentItem.use());
                                heartBox.getChildren().clear();
                                 for(int i=0;i<player.getHealth();i++)
                                        {
                                            ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                                            imgv.setFitHeight(30);
                                            imgv.setFitWidth(30);
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {

                                                    heartBox.getChildren().add(imgv);
                                                }

                                            });
                                        }

                            }break;
                            case "SP": {
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);
                                System.out.println("Drinking speed: "+player.getSpeed());


                                player.setSpeed(player.getSpeed()/2);

                            }break;

                            case "TIME": {
                                System.out.println("Drinking time: "+player.getSpeed());
                                scene.setCursor(Cursor.DEFAULT);
                                inventoryMenu.dropItem(currentItem);
                                freezeTime=true;
                                time=(long)currentItem.use();
                                freezeLength=time;


                            }break;
                            default:break;
                        }



                    }
                });
        returnButton.setOnAction(
                e->{
                    if (currentItem!=null)
                        scene.setCursor(Cursor.DEFAULT);
                    currentItem=null;

                });
        HBox hbox=new HBox();
        hbox.getChildren().addAll(consumeButton,returnButton,heartBox);
        hbox.setPadding(new Insets(15.0));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(25);
        window.setTop(hbox);
        if(player!=null)
            if(player.getHealth()>0)
            {

                for(int i=0;i<player.getHealth();i++)
                {
                    ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                    imgv.setFitHeight(30);
                    imgv.setFitWidth(30);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            heartBox.getChildren().add(imgv);
                        }

                    });
                }
            }
        return window;
    }

    @Override
    public boolean hasStarted() {
        return !gameOver;
    }

    @Override
    public void setStop(boolean thread) {
        gameOver=!thread;

    }

    public void addTrap(PositionedObstacle trap,Trap tr, Tile t,Tile at)
    {
        at.setTrap(true);
        t.addObstacle(trap);
        t.setTrapper(true);
        tr.setOnAction(e->{
            if(currentItem!=null)
            {
                scene.setCursor(Cursor.DEFAULT);
                if(tr.destroyTrap(currentItem)){

                    tr.open(300).play();
                    at.setTrap(false);
                    t.setTrapper(false);

                }
            }
        });



    }
    private void addItem(PositionedItem item,Item p, Tile t)
    {

            t.addItem(item);



                p.setOnAction(e->
                {
                    System.out.println("clicked***");
                    t.removeItem(item);
                    invLabel.setText(inventoryMenu.getItemCount() + 1 + "");

                    // it.setPlayAnimation(false);
                    if (!inventoryMenu.isFull()) {
                        p.saveItem();
                        //  inventoryMenu = new InventoryUI(scene, 4);
                        currentItem=inventoryMenu.getItem();

                    } else {


                    }
                });



    }
    private void addGem(PositionedItem item,Gem g,Tile t)
    {
            t.addItem(item);


                g.setOnAction(e->
                {

                    t.removeItem(item);

                    switch(g.getID().toLowerCase())
                    {
                        case "gem":pgem++;
                            break;
                        case "bgem":bgem++;
                            break;
                        case "ggem":ggem++;
                            break;
                        default:break;

                    }
                });



    }
    private void tweenDoor(Tile A, Tile B, Door door, Door doorB, PositionedObstacle obs, PositionedObstacle obs2, TILETYPE newTA, TILETYPE newTB)
    {
        door.setOnAction(e -> {
            System.out.println("Current Item: "+currentItem);
            if (currentItem!=null) {
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
                        System.out.println( "Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });
        doorB.setOnAction(e -> {
            System.out.println("Current Item: "+currentItem);
            if (currentItem!=null) {
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
                        System.out.println( "Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });

        A.addObstacle(obs,newTA);
        B.addObstacle(obs2,newTB);

    }
    private void addDoor(Door door, PositionedObstacle obs, Tile t, TILETYPE newT)
    {
        door.setOnAction(e -> {
            System.out.println("Current Item: "+currentItem);
            if (currentItem!=null) {
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
                        System.out.println( "Invalid Target\nUse Item: " + door.getID());
                    }
            }
        });

        t.addObstacle(obs,newT);
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
                        if(nextTile.isTrap(currentTile))
                        {
                            player.setHealth(player.getHealth()-1);
                            heartBox.getChildren().clear();
                            for(int i=0;i<player.getHealth();i++)
                            {
                                ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                                imgv.setFitHeight(30);
                                imgv.setFitWidth(30);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {

                                        heartBox.getChildren().add(imgv);
                                    }

                                });
                            }
                        }
                        currentTile=nextTile;

                    }
                    else canMove=false;

                    System.out.println("Can Player move: "+canMove);

                }
                else
                if(whatDir.equals("left"))
                {
                    nextTile=currentTile.nextTile("east");
                    if(nextTile.isTrap(currentTile))
                    {
                        player.setHealth(player.getHealth()-1);
                        heartBox.getChildren().clear();
                        for(int i=0;i<player.getHealth();i++)
                        {
                            ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                            imgv.setFitHeight(30);
                            imgv.setFitWidth(30);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    heartBox.getChildren().add(imgv);
                                }

                            });
                        }
                    }
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
                    if(nextTile.isTrap(currentTile))
                    {
                        player.setHealth(player.getHealth()-1);
                        heartBox.getChildren().clear();
                        for(int i=0;i<player.getHealth();i++)
                        {
                            ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                            imgv.setFitHeight(30);
                            imgv.setFitWidth(30);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    heartBox.getChildren().add(imgv);
                                }

                            });
                        }
                    }
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
                    if(nextTile.isTrap(currentTile))
                    {
                        player.setHealth(player.getHealth()-1);
                        heartBox.getChildren().clear();
                        for(int i=0;i<player.getHealth();i++)
                        {
                            ImageView imgv=new ImageView(new Image(getClass().getResourceAsStream("/Images/heart.png")));
                            imgv.setFitHeight(30);
                            imgv.setFitWidth(30);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    heartBox.getChildren().add(imgv);
                                }

                            });
                        }
                    }
                    if(nextTile!=null){
                        canMove = true;
                        currentTile=nextTile;
                    }
                    else canMove=false;
                    System.out.println("Can Player move: "+canMove);

                }





                stack.getChildren().setAll(currentTile,roomLbl,playerImage);

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


    public StackPane youLose()
    {
        StackPane stackPane=new StackPane();
        ImageView background=new ImageView(new Image(getClass().getResourceAsStream("/Images/youLose.png")));
        ImageView player=new ImageView(new Image(getClass().getResourceAsStream("/Images/hero.png")));
        VBox box=new VBox();
        box.setPadding(new Insets(15));
        box.setSpacing(10);
        box.setMaxSize(300,300);
        box.setTranslateY(150);
        TextField nameField=new TextField();

        box.setAlignment(Pos.CENTER);

        stackPane.getChildren().addAll(background,player);
        return  stackPane;
    }
    private  long time=0;
    @Override
    public void run() {

        while(!gameOver||!dead)
        {

            if(player!=null)
            if(player.getHealth()<=0)
            {
                dead=true;

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                      window.setCenter(youLose());
                    }
                });

            }



            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    roomLbl.setText(currentTile.getID().toUpperCase());
                }
            });

            if(freezeTime)
            {
                if(time>0)
                    time-=1000;
                System.out.println("Time: "+time);
                freezeLength=time;
                Label timeLabel=new Label();



                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        window.setLeft(timeLabel);
                        timeLabel.setFont(new Font(32));
                        if(time<5000)
                            timeLabel.setTextFill(Color.RED);
                        if(time==0)
                            timeLabel.setText("");

                        else timeLabel.setText(new SimpleDateFormat("ss").format(time));

                    }
                });
                if(time<=0){
                    freezeTime=false;

                }

            }

            if(currentItem!=null)
            {
                returnButton.setDisable(false);
                consumeButton.setDisable(false);
            }


            if(inventoryMenu!=null)
                currentItem=inventoryMenu.getItem();
            if(currentTile!=null)
            {

                if(currentTile.getTileType()== TILETYPE.GOALA)
                    gameOver=true;
                else  if(currentTile.getTileType()== TILETYPE.GOALB)
                    gameOver=true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                System.out.println("Thread Interrupted");
                gameOver=true;
            }
        }
    }
}
