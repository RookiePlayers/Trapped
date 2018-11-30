package Maze;

import Leaderboard.Models.Leaderboard;
import Leaderboard.UI.LeaderboardGUI;
import inventory.Effects;
import inventory.Models.Inventory;
import inventory.Models.Item;
import inventory.UI.InventoryUI;
import javafx.animation.AnimationTimer;
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

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;

public  class ClassicMazeDecorator  extends MazeDecorator implements  Runnable{

    private Maze maze;
    private Label timerLabel;
    private long time;
    private AnimationTimer timer;
    private BorderPane window;
    private String name="Player"+(int)(Math.random()*(200));
    public ClassicMazeDecorator(Maze maze) {
        super(maze);
        this.maze=maze;
        timerLabel=new Label();
        timerLabel.setFont(new Font("Thoma",24));
        timerLabel.setTextFill(Color.WHITE);
    }
    @Override
    public BorderPane initMaze(Scene scene, InventoryUI inventoryMenu, Label invLabel, Item item, Player player, ArrayList<Item> items, Inventory inventory) //getMaze()
    {
        timerLabel=new Label();
        timerLabel.setFont(new Font(24));
        timerLabel.setTextFill(Color.BLACK);
        System.out.println("INITIALIZING MASE AT CLASSIC ");
         window=maze.initMaze(scene,inventoryMenu,invLabel,item,player,items,inventory);
        timerLabel.setText("0 sec");
        HBox box=new HBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20.0);
        box.setPadding(new Insets(12.0));
        box.getChildren().addAll(new Label("Time Elapsed: "),timerLabel);

        window.setTop(box);
        window.setEffect(Effects.DROP_SHADOW());
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
                    timerLabel.setText(Long.toString(time)+" sec(s)");
                }
            }
            @Override
             public  String toString()
            {
                return  time+"";
            }
        };
        return window;
    }
    public void startMaze(Scene scene)
    {
        maze.startMaze(scene);

    }
    public StackPane resetMainMenu()
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
        complete.setText("Congratulations!\nYour Time: "+timer.toString()+" sec(s)");
        complete.setFont(new Font(26));
        nameField.setPromptText("Enter a Name..");
        Button save=new Button("Save to LeaderBoard");
        Button viewLeaderBoard= new Button("View Leaderboard");
        Button replay= new Button("Play Again");

        if(!nameField.getText().equals(""))
            name=nameField.getText();
        Leaderboard leaderboard=new Leaderboard("classic_leaderboard.txt");
        save.setOnAction(
                e->{
                    leaderboard.saveToLeaderBoard("classic_leaderboard.txt",name,timer.toString()+" sec(s)");
                    save.setDisable(true);
                }
        );

        viewLeaderBoard.setOnAction(e->{
            LeaderboardGUI gui=new LeaderboardGUI("CLASSIC LEADERBOARD","classic_leaderboard.txt");

            gui.showAndWait();
        });




        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(complete,nameField,save,viewLeaderBoard,replay);
        stackPane.getChildren().addAll(background,player,box);
        return  stackPane;
    }

    @Override
    public void run() {time=0;
    boolean start=true;
        while(start)
        {
            try {
                Thread.sleep(1000);
               // System.out.println(new SimpleDateFormat("hh:MM:ss").format(Duration.between()));
                if(time==0)timer.start();

                if(maze.isGameOver())
                {
                    timer.stop();
                    StackPane stackPane=resetMainMenu();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            window.setCenter(stackPane);
                        }
                    });


                    start=false;
                    //save data here

                }

                time+=1000;

              /*  Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(time);
                       // timerLabel.setText(new SimpleDateFormat("hh:MM:ss").format(time));

                    }
                });*/

                       } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
