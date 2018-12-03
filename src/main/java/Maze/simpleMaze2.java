package Maze;

//import trappedGame.mainScreen;

import Maze.MazeDifficulty.Coord;
import Maze.MazeDifficulty.PositionedItem;
import inventory.Models.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import javafx.event.EventHandler;
import javafx.geometry.Pos;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import javax.swing.*;


public class simpleMaze2 extends VBox{
	
	private static final int KEYBOARD_MOVEMENT_DELTA = 700;
	private static final Duration TRANSLATE_DURATION = Duration.seconds(2.5);
	private boolean canMove = true;
	private String whatDir = "";
	private ImageView mazeImage = new ImageView();
	private ImageView selectedImage = new ImageView();
	private StackPane stack = new StackPane();
	private final TranslateTransition transition = createTranslateTransition(selectedImage);
	private Image image1 = new Image(getClass().getResourceAsStream("/Images/hero.png"));
	private int currentMaze = 0;
	private String whereInMaze = "a";
	private int n = 4;
	private Player player=new Player();
	private ArrayList<Item> items=new ArrayList<>();
	private Inventory inventory;
	private Item currentItem;
	private Obstacle currentObstacle;


	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	/*public simpleMaze2()
	{
		setScene(getMaze());
        initModality(Modality.APPLICATION_MODAL);
        //setTitle("High Score");
	}*/
	
	public VBox simpleMaze2(Scene scene, Player player, ArrayList<Item> items,Inventory inventory) //getMaze()
	{
		this.player=player;
		this.items=items;
		this.inventory=inventory;
		//initModality(Modality.APPLICATION_MODAL);
		//Group root = new Group(startMaze());
		//Scene scene = new Scene(startMaze(), 400, 400);
		//Pane pane = new Pane();
		Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
		Image image1 = new Image(getClass().getResourceAsStream(player.getUrl()));
		mazeImage.setImage(image2);
		selectedImage.setImage(image1);//player


		//TranslateTransition transition = createTranslateTransition(selectedImage);

		//pane.getChildren().add(mazeImage);
		//Scene scene = new Scene(pane);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		//StackPane stack = new StackPane();
		vbox.getChildren().add(stack);
		
		stack.setAlignment(Pos.CENTER);
		stack.getChildren().addAll(mazeImage, selectedImage);//player and current maze
		
		startMaze(scene);

		return vbox;
	}
	
	public void startMaze(Scene scene)
	{
		for (Item it : inventory.getItems())
			it.setOnAction(e -> {

				if (!inventory.isMaxSize(inventory.getSize())) {
					it.saveItem();



					stack.getChildren().remove(it);


				} else {

					JOptionPane.showMessageDialog(null, "Oops Sorry Your InventoryInterface is Full");

				}
			});



		//final ImageView mazeImage = new ImageView(); 
		Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
		//int n = (int) (Math.random()*5)+1;
		if (n == 1)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze1.png"));
			currentMaze = 1;
		}
		if (n == 2)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze2.png"));
			currentMaze = 2;
		}
		if (n == 3)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze3.png"));
			currentMaze = 3;
		}
		if (n == 4)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
			currentMaze = 4;
		}
		if (n == 5)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze5.png"));
			currentMaze = 5;
		}
		
		//mazeImage.setImage(image2);
		mazeImage = new ImageView(image2);
		  
        //selectedImage.setImage(image1);
		selectedImage = new ImageView(image1);//player
		//selectedImage.setX(200);
		//selectedImage.setY(275);
		
		//final Group group = new Group(mazeImage, selectedImage);
		//final TranslateTransition transition = createTranslateTransition(selectedImage);
		
		//final Scene scene = new Scene(group, 800, 800, Color.BLUE);
		
		//moveImage(scene, selectedImage);
		//TranslateTransition transition = createTranslateTransition(selectedImage);
		moveImage(scene, selectedImage, transition);
		
		//stage.setScene(scene);
		//stage.show();
		//Stage secondaryStage;
		//return secondaryStage;
	  }

	
	private void moveImage(Scene scene, final ImageView image, TranslateTransition transition)
	{
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() 
		{
			public void handle(KeyEvent event) 
			{
				if (canMove)
				{
					switch (event.getCode()) 
					{
						case UP: if (currentMaze != 2 && currentMaze != 6 && currentMaze != 7 && currentMaze != 10) {transition.setToY(image.getY() - (KEYBOARD_MOVEMENT_DELTA - 320)); canMove = false; transition.playFromStart();whatDir = "up";} break;
						case RIGHT: if (currentMaze != 1 && currentMaze != 6 && currentMaze != 8) {transition.setToX(image.getX() + (KEYBOARD_MOVEMENT_DELTA - 300)); canMove = false; transition.playFromStart();whatDir = "right";} break;
						case LEFT: if (currentMaze != 3 && currentMaze != 7 && currentMaze != 8) {transition.setToX(image.getX() - (KEYBOARD_MOVEMENT_DELTA - 300)); canMove = false; transition.playFromStart();whatDir = "left";} break;
						case DOWN: if (currentMaze != 5 && currentMaze != 6 && currentMaze != 7 && currentMaze != 8 && currentMaze != 10) {transition.setToY(image.getY() + (KEYBOARD_MOVEMENT_DELTA - 320)); canMove = false; transition.playFromStart();whatDir = "down";} break;
					}
				}
			}
		});
    }
	private Item itemGenerator()
	{
		int rand=(int)(Math.random()*(items.size()));
		if(items.size()>1)
			return items.get(rand);
		else
			return new HealthPotion();
	}
	private TranslateTransition createTranslateTransition(final ImageView image) {
    TranslateTransition transition = new TranslateTransition(TRANSLATE_DURATION, image);
    transition.setOnFinished(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent t) {
		image.setX(image.getTranslateX() + image.getX());
        image.setY(image.getTranslateY() + image.getY());
        image.setTranslateX(0);
        image.setTranslateY(0);
		transition.setToX(0);
		transition.setToY(0);
		Image image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
		//int n = (int) (Math.random()*5)+1;
		if(whereInMaze.equals("a") && whatDir.equals("right"))
		{
			whereInMaze = "b";
			currentItem=itemGenerator();
			System.out.println("ROOMB");
			n = 5;
		}else if(whereInMaze.equals("a") && whatDir.equals("left"))
		{
			currentItem=itemGenerator();
			whereInMaze = "c";
			n = 5;
		}else if(whereInMaze.equals("b") && whatDir.equals("right"))
		{
			currentItem=itemGenerator();
			whereInMaze = "d";
			n = 6;
		}else if(whereInMaze.equals("b") && whatDir.equals("left"))
		{
			whereInMaze = "a";
			n = 4;

		}else if(whereInMaze.equals("a") && whatDir.equals("down"))
		{
			currentItem=itemGenerator();
			whereInMaze = "f";
			n = 8;
		}else if(whereInMaze.equals("f") && whatDir.equals("up"))
		{
			whereInMaze = "a";
			n = 4;
		}else if(whereInMaze.equals("d") && whatDir.equals("left"))
		{
			currentItem=itemGenerator();
			whereInMaze = "b";
			n = 5;
		}else if(whereInMaze.equals("c") && whatDir.equals("right"))
		{
			whereInMaze = "a";
			n = 4;
		}else if(whereInMaze.equals("c") && whatDir.equals("left"))
		{
			currentItem=itemGenerator();
			whereInMaze = "e";
			n = 7;
		}else if(whereInMaze.equals("e") && whatDir.equals("right"))
		{
			whereInMaze = "c";
			n = 5;
		}else if(whereInMaze.equals("a") && whatDir.equals("up"))
		{
			whereInMaze = "g";
			n = 2;
		}else if(whereInMaze.equals("b") && whatDir.equals("up"))
		{
			currentItem=itemGenerator();
			whereInMaze = "h";
			n = 2;
		}else if(whereInMaze.equals("c") && whatDir.equals("up"))
		{
			currentItem=itemGenerator();
			whereInMaze = "i";
			n = 2;
		}else if(whereInMaze.equals("g") && whatDir.equals("down"))
		{
			whereInMaze = "a";
			n = 4;
		}else if(whereInMaze.equals("h") && whatDir.equals("down"))
		{
			whereInMaze = "b";
			n = 5;
		}else if(whereInMaze.equals("i") && whatDir.equals("down"))
		{
			currentItem=itemGenerator();
			whereInMaze = "c";
			n = 5;
		}else if(whereInMaze.equals("i") && whatDir.equals("left"))
		{
			whereInMaze = "k";
			n = 7;
		}else if(whereInMaze.equals("k") && whatDir.equals("right"))
		{
			whereInMaze = "i";
			n = 2;
		}else if(whereInMaze.equals("i") && whatDir.equals("right"))
		{
			currentItem=itemGenerator();
			whereInMaze = "g";
			n = 2;
		}else if(whereInMaze.equals("g") && whatDir.equals("right"))
		{
			whereInMaze = "h";
			n = 2;
		}else if(whereInMaze.equals("g") && whatDir.equals("left"))
		{
			currentItem=itemGenerator();
			whereInMaze = "i";
			n = 2;
		}else if(whereInMaze.equals("h") && whatDir.equals("right"))
		{
			whereInMaze = "j";
			n = 10;
		}else if(whereInMaze.equals("j") && whatDir.equals("left"))
		{
			whereInMaze = "h";
			n = 2;
		}else if(whereInMaze.equals("j") && whatDir.equals("right"))
		{
			whereInMaze = "out";
			n = 100;
			canMove = false;
		}
		
		
		if (n == 1)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze1.png"));
			currentMaze = 1;
		}
		if (n == 2)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze2.png"));
			currentMaze = 2;
		}
		if (n == 3)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze3.png"));
			currentMaze = 3;
		}
		if (n == 4)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze4.png"));
			currentMaze = 4;
		}
		if (n == 5)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze5.png"));
			currentMaze = 5;
		}
		if (n == 6)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze6.png"));
			currentMaze = 6;
		}
		if (n == 7)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze7.png"));
			currentMaze = 7;
		}
		if (n == 8)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/maze8.png"));
			currentMaze = 8;
		}
		if (n == 10)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/exitRight.png"));
			currentMaze = 10;
		}
		if (n == 100)
		{
			image2 = new Image(getClass().getResourceAsStream("/Images/youWin.png"));
			currentMaze = 100;
		}
		
		stack.getChildren().remove(mazeImage);
		//stack.getChildren().clear();
		mazeImage.setImage(image2);
		//selectedImage.setImage(image1);
		//transition = createTranslateTransition(selectedImage);
		stack.getChildren().addAll(mazeImage);
		if(currentItem!=null)
			stack.getChildren().addAll(new PositionedItem(currentItem,new Coord(0,0)));
		ObservableList<Node> workingCollection = FXCollections.observableArrayList(stack.getChildren());
		Collections.swap(workingCollection, 1, 2);
		stack.getChildren().setAll(workingCollection);
		canMove = true;
		if (n == 100)
		{
			stack.getChildren().clear();
			mazeImage.setImage(image2);
			stack.getChildren().addAll(mazeImage);
			//selectedImage.setImage(image1);
			//selectedImage.setX(200);
			//selectedImage.setY(275);
			canMove = false;
		}
      }
    });
    return transition;
  }
  

	//public static void main(String[] args) {
        //launch(args);
    //}
}
