package Main;//import java.-classpath.Trapped.Main.Trapped;
//import java.-classpath.trappedGame.simpleMaze;

import Leaderboard.UI.LeaderboardGUI;
import Maze.Maze;
import Maze.*;
import Maze.MazeMenu;
import inventory.Models.*;
import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Parent;
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
import Maze.simpleMaze2;

import java.util.ArrayList;


public class Trapped extends Stage
{

	Scene scene,scene2,scene3;

		//starts method in application class
	public Trapped()
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

        Button b1 = new Button("Play");
       	b1.setStyle("-fx-border-color: blue;");
		b1.setMinWidth(120);
		b1.setMinHeight(20);
		hbox.getChildren().addAll(b1);
		//b1.setOnAction(e -> setScene(scene2));
		b1.setOnAction(e -> {
			close();
			GameType gameMode=new GameType(inventory);
			gameMode.getMainMenu().setOnAction(exit->{gameMode.close();show();});
			gameMode.showAndWait();
			/*MazeMenu mazemenu=new MazeMenu(inventory,MAZETYPE.SIMPLE);


			mazemenu.getExit().setOnAction(exit->{mazemenu.close();close();});
			mazemenu.getBack().setOnAction(exit->{mazemenu.close();show();});
			mazemenu.showAndWait();

			//newGame.launch(simpleMaze.class);
			//Platform.exit();
			//this.dispose();
			//try{
				//setScene(newGame.startMaze(primaryStage));
			//}catch(Exception er)
			//{ System.out.println("ERROR");}*/
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
		b3.setOnAction(e -> setScene(scene));

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
		b4.setOnAction(e -> setScene(scene));




		Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

		setX(bounds.getMinX());
		setY(bounds.getMinY());
		setWidth(bounds.getWidth());
		setHeight(bounds.getHeight());
		setTitle("Trapped"); // Set the stage title
		setScene(scene); // Place the scene in the stage
		show(); // Display the stage

	}

	
}
class GameType extends Stage
{
	private Button mainMenu=new Button("Main Menu");
	private MAZETYPE mazetype;
	private GAMEMODES gamemodes;
	private Inventory inventory;

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

	public GameType(Inventory inventory) {

		this.inventory=inventory;
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		setScene(gameType());
		setX(bounds.getMinX());
		setY(bounds.getMinY());
		setWidth(bounds.getWidth());
		setHeight(bounds.getHeight());
		setTitle("Game Mode"); // S
	}
	public Scene gameType()
	{


		BorderPane borderPane=new BorderPane();
		VBox box=new VBox();

		Button classicMaze=new Button("CLASSIC");
		Button timeTrial=new Button("TIME TRIAL");
		Button itemHunter=new Button("ItemHunter");

		classicMaze.setOnAction(
				e->{
					setScene(gameDifficulty());
					gamemodes=GAMEMODES.CLASSIC;
				}
		);
		timeTrial.setOnAction(
				e->{
					setScene(gameDifficulty());
					gamemodes=GAMEMODES.TIMECHALLENGE;
				}
		);
		itemHunter.setOnAction(
				e->{
					setScene(gameDifficulty());
					gamemodes=GAMEMODES.ITEMHUNTER;
				}
		);

		box.getChildren().addAll(classicMaze,timeTrial,itemHunter,mainMenu);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(15);
		borderPane.setCenter(box);



		Scene sc=new Scene(borderPane);

		return sc;
	}
	public Scene gameDifficulty()
	{

		BorderPane borderPane=new BorderPane();
		VBox box=new VBox();
		Button hardMaze=new Button("Hard");
		Button intermediateMaze=new Button("Intermediate");
		Button easyMaze=new Button("Easy");

		hardMaze.setOnAction(e->{
			mazetype=MAZETYPE.HARD;
			MazeMenu mazemenu=new MazeMenu(inventory,mazetype,gamemodes);


			mazemenu.getExit().setOnAction(exit->{mazemenu.close();setScene(gameType());});
			mazemenu.getBack().setOnAction(exit->{mazemenu.close();show();});
			mazemenu.showAndWait();

		});
		intermediateMaze.setOnAction(e->{
			mazetype=MAZETYPE.MEDIUM;
			MazeMenu mazemenu=new MazeMenu(inventory,mazetype,gamemodes);


			mazemenu.getExit().setOnAction(exit->{mazemenu.close();setScene(gameType());});
			mazemenu.getBack().setOnAction(exit->{mazemenu.close();show();});
			mazemenu.showAndWait();
		});
		easyMaze.setOnAction(e->{
			mazetype=MAZETYPE.SIMPLE;
			MazeMenu mazemenu=new MazeMenu(inventory,mazetype,gamemodes);


			mazemenu.getExit().setOnAction(exit->{mazemenu.close();setScene(gameType());});
			mazemenu.getBack().setOnAction(exit->{mazemenu.close();show();});
			mazemenu.showAndWait();
		});


		Button backBtn=new Button("BACK");
		backBtn.setOnAction(e->{
			setScene(gameType());
		});
		box.getChildren().addAll(hardMaze,intermediateMaze,easyMaze,backBtn);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(15);
		borderPane.setCenter(box);

		Scene sc=new Scene(borderPane);
		return sc;
	}
}