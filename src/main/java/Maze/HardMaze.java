package Maze;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HardMaze extends Maze {
    private boolean gameOver;
    private Tile a,b,c,d,e,f,g,h,i,j,k,nextTile;

    @Override
    public void setup() {
        //all thats needed


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
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
