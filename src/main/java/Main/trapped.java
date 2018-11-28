package Main;//import java.-classpath.Trapped.Main.trapped;
//import java.-classpath.trappedGame.simpleMaze;

import Leaderboard.UI.LeaderboardGUI;
import Maze.Maze;
import Maze.*;
import Maze.MazeMenu;
import inventory.Models.*;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import Maze.simpleMaze2;

import java.util.ArrayList;


public class trapped extends Application
{

	Scene scene,scene2,scene3;

	@Override	//starts method in application class
	public void start(Stage primaryStage)
	{
		//scenes


        //Scene 1 Layout
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		HBox hbox = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);
		hbox3.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(hbox3,hbox,hbox2);


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
		sp.setPlayAnimation(true);

		TimeFreeze tf = new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 20);
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
		Inventory inventory=new Inventory(items,false);

		
		//Scene 1
        scene = new Scene(vbox);
        scene.setFill(Color.WHITE);
		
		//Platform.setImplicitExit(false);
		

		final simpleMaze2 newGame = new simpleMaze2();
		
		//Scene 1 Buttons

        Button b1 = new Button("Play Simple Maze");
       	b1.setStyle("-fx-border-color: blue;");
		b1.setMinWidth(120);
		b1.setMinHeight(20);
		hbox.getChildren().addAll(b1);
		//b1.setOnAction(e -> primaryStage.setScene(scene2));
		b1.setOnAction(e -> {
			primaryStage.close();
			MazeMenu mazemenu=new MazeMenu(inventory,MAZETYPE.SIMPLE);


			mazemenu.getExit().setOnAction(exit->{mazemenu.close();primaryStage.close();});
			mazemenu.getBack().setOnAction(exit->{mazemenu.close();primaryStage.show();});
			mazemenu.showAndWait();

			//newGame.launch(simpleMaze.class);
			//Platform.exit();
			//this.dispose();
			//try{
				//primaryStage.setScene(newGame.startMaze(primaryStage));
			//}catch(Exception er)
			//{ System.out.println("ERROR");}
		});

		//XzibitVideo programVideo = new XzibitVideo();
		//programVideo.launch(XzibitVideo.class);
		//this.dispose();

		Button b2 = new Button("Leaderboard");
		b2.setStyle("-fx-border-color: blue;");
		b2.setMinWidth(120);
		b2.setMinHeight(20);
		hbox2.getChildren().addAll(b2);
		b2.setOnAction(e -> new LeaderboardGUI().showAndWait());

		Image logo = new Image(getClass().getResourceAsStream("/Images/maze.png"));
		ImageView imageView = new ImageView();
        imageView.setImage(logo);
        imageView.setCache(true);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
		hbox3.getChildren().add(imageView);





		//Scene 2 Layout
        VBox vbox2 = new VBox();
		vbox2.setAlignment(Pos.CENTER);
		HBox hbox4 = new HBox();
		hbox4.setAlignment(Pos.CENTER);
		vbox2.getChildren().add(hbox4);

		//Scene 2
        scene2 = new Scene(vbox2);
        scene2.setFill(Color.WHITE);

		//Scene 2 Buttons
		Button b3 = new Button("Back");
       	b3.setStyle("-fx-border-color: blue;");
		b3.setMinWidth(120);
		b3.setMinHeight(20);
		hbox4.getChildren().add(b3);
		b3.setOnAction(e -> primaryStage.setScene(scene));

		//Scene 3 Layout
        VBox vbox3 = new VBox();
		vbox3.setAlignment(Pos.CENTER);
		HBox hbox5 = new HBox();
		hbox5.setAlignment(Pos.CENTER);
		vbox3.getChildren().add(hbox5);

		//Scene 2
        scene3 = new Scene(vbox3);
        scene3.setFill(Color.WHITE);
        

		//Scene 2 Buttons
		Button b4 = new Button("Back");
       	b4.setStyle("-fx-border-color: blue;");
		b4.setMinWidth(120);
		b4.setMinHeight(20);
		hbox5.getChildren().add(b4);
		b4.setOnAction(e -> primaryStage.setScene(scene));




		Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		primaryStage.setTitle("Trapped"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	public static void main(String[] args) { 
		launch(args);
  }
}