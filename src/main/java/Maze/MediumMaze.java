package Maze;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public class MediumMaze extends Maze {
    private boolean gameOver;

    @Override
    public void setup() {

    }

    @Override
    public void run() {
        while(!gameOver)
        {
            if(inventoryMenu!=null)
                currentItem=inventoryMenu.getItem();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    protected TranslateTransition createTranslateTransition(final ImageView image) {
        return null;
    }
}
