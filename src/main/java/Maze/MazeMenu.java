package Maze;

import Interface.MazeInterface;
import inventory.Effects;
import inventory.Models.*;
import inventory.UI.InventoryUI;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

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
    private  MazeFactory mazeFactory;


    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
    public void closeThreads()
    {
        if(mazeFactory!=null) {
            mazeFactory.getMaze().setStop(false);//false=stop

                mazeFactory.getMazeThread().interrupt();
                mazeFactory.getStyleThread().interrupt();


        }
    }
    public MazeMenu(Inventory inventory,MAZETYPE mazeType,GAMEMODES gamemodes)
    {
        System.out.println("Playing "+gamemodes+" on "+mazeType+" Dificulty");
        MazeInterface maze;
        this.inventory=inventory;
        Inventory startOffInventory=new Inventory();
        Player player=new Player("Player"+((int)(Math.random()*(1000-1))),"/Images/hero.png",3,startOffInventory);

        inventory.setDuplicates(true);
        BorderPane borderPane=new BorderPane();
        borderPane.setPadding(new Insets(20));

        back=new Button("Back");
        exit=new Button("Exit");
        top=new HBox();

        switch(mazeType) {
            case SIMPLE: {
                ArrayList<Item> items = ITEMS.itemList(0, 1, 2,0);

                for (Item it : items)
                {
                    System.out.println("ITEMSSS>> "+it);
                    inventory.addItem(it);
                }
            }break;
            case MEDIUM: {
                ArrayList<Item> items = ITEMS.itemList(2, 2, 2,1);

                for (Item it : items)
                {

                    inventory.addItem(it);
                }
            }break;
            case HARD: {
                ArrayList<Item> items = ITEMS.itemList(5, 3, 5,6);

                for (Item it : items)
                {

                    inventory.addItem(it);
                }
            }break;
        }
        if(gamemodes==GAMEMODES.ITEMHUNTER)
        {
            //addGems
            switch(mazeType) {
                case SIMPLE: {
                    ArrayList<Gem> gems = Maze.GEMMY.gemList(10, 2, 5);

                    for (Gem it : gems)
                    {
                        System.out.println(it.getName());
                        inventory.addGem(it);
                    }
                }break;
                case MEDIUM: {
                    ArrayList<Gem> gems = Maze.GEMMY.gemList(15, 5, 8);
                    for (Gem it : gems)
                        inventory.addGem(it);
                }break;
                case HARD: {
                    ArrayList<Gem> gems = Maze.GEMMY.gemList(20, 8, 12);
                    for (Gem it : gems)
                        inventory.addGem(it);
                }break;

            }

        }

         mazeFactory=new MazeFactory();
        maze=mazeFactory.makeMaze(mazeType,gamemodes);
       // setTitle(maze.getTitle());
        //title=maze.getTitle();
        ((HBox) top).getChildren().addAll(exit,new Label(title),back);

        back.setAlignment(Pos.TOP_LEFT);
        exit.setAlignment(Pos.TOP_RIGHT);
        ((HBox) top).setAlignment(Pos.CENTER);
        ((HBox) top).setSpacing(20.0);



        bottom=new HBox();
        ((HBox) bottom).getChildren().addAll();

        VBox vv=new VBox();
        vv.getChildren().add(borderPane);
        Scene scene=new Scene(vv);
        setScene(scene);
        System.out.println(player.getName());


        inventoryUI = new InventoryUI(scene, 4);
        middle=maze.initMaze(scene,inventoryUI,invLabel,item,player,null,inventory);
    invLabel.setText(inventoryUI.getItemCount()+"");




        borderPane.setTop(top);
        borderPane.setBottom(bottom);
        borderPane.setCenter(middle);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setHeight(bounds.getHeight());
        setWidth(bounds.getWidth());


        scene.getStylesheets().add(getClass().getResource("/css/bag.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("/css/gameScreen.css").toExternalForm());
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
invLabel.setTextFill(Color.WHITE);
    vv.getStyleClass().add("background");


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

    static  class ITEMS
    {
        public static Item HEALTHPOTIONS= new HealthPotion("healthpotion", "+50 HP", "Adds 50 HP", 50, 50, true, 50);
        public static Item SPEEDPOTIONS= new SpeedPotion("speedpotion", "x2 Speed", "Doubles your Speed", 50, 50, true, 2);
        public static Item TIMEFREEZER= new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 2000);
        public static Item TBR= new TrapBreaker("tbr", "tbr", "", 50, 50, true, 0);

        public static ArrayList<Item>itemList(int hp,int sp,int tf,int tbr)
        {
            ArrayList<Item> items=new ArrayList<>();
            Item hps[]=new Item[hp];
            Item sps[]=new Item[sp];
            Item tfs[]=new Item[tf];
            Item tbs[]=new Item[tbr];

            for(int i=0;i<hp;i++)
            {

                hps[i]=new HealthPotion("healthpotion", "+50 HP", "Adds 50 HP", 50, 50, true, 50);;
                items.add(hps[i]);
            }
            for(int i=0;i<sp;i++)
            {
                sps[i]=new SpeedPotion("speedpotion", "x2 Speed", "Doubles your Speed", 50, 50, true, 2);;
                items.add(sps[i]);
            }
            for(int i=0;i<tf;i++)
            {
                tfs[i]= new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 2000);
                items.add(tfs[i]);
            }
            for(int i=0;i<tbr;i++)
            {
                tbs[i]=new TrapBreaker("tbr", "tbr", "", 50, 50, true, 0);
                items.add(tbs[i]);
            }
            return items;

        }
    }

}
