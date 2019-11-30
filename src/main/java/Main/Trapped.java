package Main;//import java.-classpath.Trapped.Main.Trapped;
//import java.-classpath.trappedGame.simpleMaze;

import Leaderboard.UI.*;
import Maze.*;
import inventory.Models.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;


import java.util.ArrayList;


public class Trapped extends Stage {

    Scene scene, scene2, scene3;

    //starts method in application class
    public Trapped() {

        //Scene Layout

        /*Initializing inventory items....*/
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
        HealthPotion hp = new HealthPotion("healthpotion", "+50 HP", "Adds 50 HP", 50, 50, true, 50);
        hp.setPlayAnimation(true);
        SpeedPotion sp = new SpeedPotion("speedpotion", "x2 Speed", "Doubles your Speed", 50, 50, true, 2);
        sp.setBoost(2);
        sp.setPlayAnimation(true);
        TimeFreeze tf = new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 2000);
        tf.setPlayAnimation(true);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(gkey);
        items.add(rkey);
        items.add(gnkey);
        items.add(bkey);
        items.add(mkey);
        items.add(hp);
        items.add(tf);
        items.add(sp);
        Inventory inventory = new Inventory(items, false);
        /**/

        /* Initializing Stage elements...*/
        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(20));
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setSpacing(15);

        scene = new Scene(mainVBox);
        scene.setFill(Color.WHITE);
        scene.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());


        Button b1 = new Button("Play");

        b1.setMinWidth(120);
        b1.setMinHeight(20);
        b1.getStyleClass().add("play");

        b1.setOnAction(e -> {
            close();
            GameType gameMode = new GameType(inventory);
            gameMode.getMainMenu().setOnAction(exit -> {
                gameMode.close();
                show();
            });
            gameMode.showAndWait();

        });


        Button b2 = new Button("Leaderboards");

        b2.setMinWidth(120);
        b2.setMinHeight(20);
        b2.setOnAction(e -> new LeaderBoardOptions().showAndWait());
        b2.getStyleClass().add("leaderboard");

        Image logo = new Image(getClass().getResourceAsStream("/Images/maze.png"));
        ImageView imageView = new ImageView(logo);
        imageView.setCache(true);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        mainVBox.getChildren().addAll(imageView, b1, b2);
        mainVBox.getStyleClass().add("background");

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setTitle("Trapped"); // Set the stage title
        setScene(scene); // Place the scene in the stage
        show(); // Display the stage
        /**/

    }


}

/**
 * GameType class
 * User selects Which game Mode they wish to play and
 * which Level of Difficulty They Wish to play in.
 */
class GameType extends Stage {
    private Button mainMenu = new Button("Main Menu");
    private MAZETYPE mazetype;
    private GAMEMODES gamemodes;
    private Inventory inventory;

    /**
     * Constructor for initializing values
     *
     * @param inventory: default invetory elements
     */
    public GameType(Inventory inventory) {

        this.inventory = inventory;
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        setScene(gameType());
        setX(bounds.getMinX());
        setY(bounds.getMinY());
        setWidth(bounds.getWidth());
        setHeight(bounds.getHeight());
        setTitle("Game Mode"); // S
    }


    public GAMEMODES getGamemodes() {
        return gamemodes;
    }

    public void setGamemodes(GAMEMODES gamemodes) {
        this.gamemodes = gamemodes;
    }

    public MAZETYPE getMazetype() {
        return mazetype;
    }

    public void setMazetype(MAZETYPE mazetype) {
        this.mazetype = mazetype;
    }

    public Button getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(Button mainMenu) {
        this.mainMenu = mainMenu;
    }

    public Scene gameType() {


        BorderPane borderPane = new BorderPane();
        VBox box = new VBox();

        Button classicMaze = new Button("CLASSIC");
        classicMaze.getStyleClass().add("classic");
        Button timeTrial = new Button("TIME TRIAL");
        timeTrial.getStyleClass().add("tt");

        Button itemHunter = new Button("ItemHunter");
        itemHunter.getStyleClass().add("ih");
        classicMaze.setOnAction(
                e -> {
                    gamemodes = GAMEMODES.CLASSIC;
                    setScene(gameDifficulty());

                }
        );
        timeTrial.setOnAction(
                e -> {
                    gamemodes = GAMEMODES.TIMECHALLENGE;
                    setScene(gameDifficulty());

                }
        );
        itemHunter.setOnAction(
                e -> {
                    gamemodes = GAMEMODES.ITEMHUNTER;
                    setScene(gameDifficulty());

                }
        );

        box.getChildren().addAll(classicMaze, timeTrial, itemHunter, mainMenu);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);
        borderPane.setCenter(box);
        mainMenu.getStyleClass().add("mm");
        box.getStyleClass().add("background");


        Scene sc = new Scene(borderPane);//gameType
        sc.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());


        return sc;
    }

    public Scene gameDifficulty() {

        BorderPane borderPane = new BorderPane();
        VBox box = new VBox();
        Button hardMaze = new Button("Hard");
        Button intermediateMaze = new Button("Intermediate");
        Button easyMaze = new Button("Easy");

        hardMaze.getStyleClass().add("h");
        easyMaze.getStyleClass().add("e");
        intermediateMaze.getStyleClass().add("m");

        hardMaze.setOnAction(e -> {
            close();
            mazetype = MAZETYPE.HARD;
            MazeMenu mazemenu = new MazeMenu(inventory, mazetype, gamemodes);


            mazemenu.getExit().setOnAction(exit -> {
                mazemenu.close();
                setScene(gameType());
                mazemenu.closeThreads();
            });
            mazemenu.getBack().setOnAction(exit -> {
                mazemenu.close();
                show();
                mazemenu.closeThreads();
            });
            mazemenu.showAndWait();

        });
        intermediateMaze.setOnAction(e -> {
            close();
            mazetype = MAZETYPE.MEDIUM;
            MazeMenu mazemenu = new MazeMenu(inventory, mazetype, gamemodes);


            mazemenu.getExit().setOnAction(exit -> {
                mazemenu.close();
                setScene(gameType());
                mazemenu.closeThreads();
            });
            mazemenu.getBack().setOnAction(exit -> {
                mazemenu.close();
                show();
                mazemenu.closeThreads();
            });
            mazemenu.showAndWait();
        });
        easyMaze.setOnAction(e -> {
            close();
            mazetype = MAZETYPE.SIMPLE;
            MazeMenu mazemenu = new MazeMenu(inventory, mazetype, gamemodes);


            mazemenu.getExit().setOnAction(exit -> {
                mazemenu.close();
                setScene(gameType());
                mazemenu.closeThreads();
            });
            mazemenu.getBack().setOnAction(exit -> {
                mazemenu.close();
                show();
                mazemenu.closeThreads();
            });
            mazemenu.showAndWait();
        });


        Button backBtn = new Button("BACK");
        backBtn.setOnAction(e -> {
            setScene(gameType());
        });
        box.getChildren().addAll(hardMaze, intermediateMaze, easyMaze, backBtn);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(15);
        borderPane.setCenter(box);

        Scene sc = new Scene(borderPane);//gameDifficulty
        sc.getStylesheets().add(getClass().getResource("/css/Trapped.css").toExternalForm());
        switch (gamemodes) {
            case CLASSIC:
                box.getStyleClass().add("background1");
                break;
            case ITEMHUNTER:
                box.getStyleClass().add("background3");
                break;
            case TIMECHALLENGE:
                box.getStyleClass().add("background2");
                break;


        }
        backBtn.getStyleClass().add("mm");

        return sc;
    }
}