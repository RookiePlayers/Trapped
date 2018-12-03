package MazeDecoration;

import Leaderboard.Models.Leaderboard;
import Leaderboard.UI.LeaderboardGUI;
import Maze.*;
import Maze.MazeDifficulty.Maze;
import inventory.Effects;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import inventory.controls.Timer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

public  class TimeChallengeMaze  extends MazeDecorator implements  Runnable{

    private Maze maze;
    private Label timerLabel;
    private long time;
    private Timer timer;
    private BorderPane window;
    private Scene scene;
    private boolean start=true;
    private String name="Player"+(int)(Math.random()*(200));
    private Player player;
    private Item item;
    private InventoryUI inventoryMenu;
    private Label invLabel;
    private ArrayList<Item> items;
    private Inventory inventory;
    public TimeChallengeMaze(Maze maze) {
        super(maze);
        this.maze=maze;
        timerLabel=new Label();
        timerLabel.setFont(new Font("Thoma",24));
        timerLabel.setTextFill(Color.WHITE);
        timer = new Timer();
    }
    @Override
    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {
        this.player=player;
        this.inventory=inventory;
        this.scene=scene;
        this.inventoryMenu=inventoryMenu;
        this.invLabel=invLabel;
        this.item=item;
        timerLabel=new Label();
        timerLabel.setFont(new Font(24));
        timerLabel.setTextFill(Color.BLACK);
        System.out.println("INITIALIZING MASE AT CLASSIC ");
        window=maze.initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);
        timerLabel.setText(new SimpleDateFormat("MM:ss").format(maze.getTimeLimit())+" sec");
        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20.0);
        box.setPadding(new Insets(12.0));
        box.getChildren().addAll(new Label("Time Left: "),timerLabel);




        window.setEffect(Effects.DROP_SHADOW());
        Button consumeButton=new Button("CONSUME");
        consumeButton.setDisable(true);
        Button returnButton=new Button("RETURN");
        returnButton.setDisable(true);

        consumeButton.setOnAction(
                e->{

                }
        );
        HBox hbox=new HBox();
        hbox.getChildren().addAll(window.getTop(),box);
        hbox.setPadding(new Insets(15.0));
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(25);
        window.setTop(hbox);
        timer = new Timer(timerLabel);
        return window;
    }
    public void startMaze(Scene scene)
    {
        this.scene=scene;
        maze.startMaze(scene);

    }

    @Override
    public void resetMaze() {
        System.out.println("resettting...");
        initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);

    }


    public StackPane youWin()
    {
        StackPane stackPane=new StackPane();
        ImageView background=new ImageView(new Image(getClass().getResourceAsStream("/Images/youWin.png")));
        ImageView player=new ImageView(new Image(getClass().getResourceAsStream("/Images/hero.png")));
        VBox box=new VBox();
        box.setPadding(new Insets(15));
        box.setSpacing(10);
        box.setMaxSize(300,300);
        box.setTranslateY(150);
        TextField nameField=new TextField();
        Label complete=new Label();
        complete.setTextFill(Color.WHITE);
        complete.setText("Congratulations!\nYour Time: "+new SimpleDateFormat("mm:ss").format(maze.getTimeLimit()-time)+" ");
        complete.setFont(new Font(26));
        nameField.setPromptText("Enter a Name..");
        Button save=new Button("Save to LeaderBoard");
        Button viewLeaderBoard= new Button("View Leaderboard");
        Button replay= new Button("Play Again");

        // if(!nameField.getText().equals(""))


        Leaderboard leaderboard=new Leaderboard("tc_leaderboard.txt");


        save.setOnAction(
                e->{
                    name=nameField.getText();
                    this.player.setName(name);
                    leaderboard.saveToLeaderBoard("tc_leaderboard.txt",name,new SimpleDateFormat("mm:ss").format(maze.getTimeLimit()-time)+"",maze.getTitle());
                    save.setDisable(true);
                }
        );

        viewLeaderBoard.setOnAction(e->{
            LeaderboardGUI gui=new LeaderboardGUI("TIME CHALLENGE LEADERBOARD","tc_leaderboard.txt");

            gui.showAndWait();
        });
        replay.setOnAction(e->{maze.resetMaze();time=0;start=true;});



        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(complete,nameField,save,viewLeaderBoard);
        stackPane.getChildren().addAll(background,player,box);
        return  stackPane;
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
    private long timeOnFrezze=0;
    private boolean firstStart=true;

    @Override
    public boolean hasStarted() {
        return start;
    }
    @Override
    public void setStop(boolean stop)
    {
        this.start=stop;
    }



    @Override
    public void run() {time=maze.getTimeLimit();

        while(start)
        {
            try {
                Thread.sleep(1000);

                // System.out.println(new SimpleDateFormat("hh:MM:ss").format(Duration.between()));
                if(firstStart){
                    System.out.println("strating...");
                    firstStart=false;
                    maze.setFreezeLength(1);
                    time=maze.getTimeLimit();

                }
                if(maze.isFreezeTime())
                {

                    time=time;
                    maze.setTimeLimit(time);
                }else if(maze.getFreezeLength()<=0)
                {
                    System.out.println(">>>"+time);
                   // timer.setTime(time);

                    firstStart=true;
                }
                else{
                    System.out.println(time);
                    if(time>=1000)
                    {
                        time-=1000;
                    }
                    else
                    {
                        StackPane stackPane=youLose();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                window.setCenter(stackPane);
                                // timerLabel.setText(new SimpleDateFormat("MM:ss").format(time));
                            }
                        });


                        start=false;
                    }

                }

                System.out.println("Freeze? "+maze.isFreezeTime());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                       // window.setCenter(stackPane);
                        timerLabel.setText(new SimpleDateFormat("mm:ss").format(time));
                    }
                });

                if(maze.isGameOver())
                {

                    StackPane stackPane=youWin();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            window.setCenter(stackPane);
                           // timerLabel.setText(new SimpleDateFormat("MM:ss").format(time));
                        }
                    });


                    start=false;
                    //save data here

                }
                if(maze.isDead())
                {
                    start=false;
                }



              /*  Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(time);
                       // timerLabel.setText(new SimpleDateFormat("hh:MM:ss").format(time));

                    }
                });*/


            } catch (InterruptedException e) {
                //e.printStackTrace();
                start=false;
                System.out.println("Thread interrupted");
            }
        }
    }
}
