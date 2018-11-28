package inventory.Models;

import Interface.ItemInterface;
import inventory.Effects;
import inventory.Models.Item;
import inventory.Models.ItemStatus;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class KeyItem extends Item {


    private double width;
    private double height;
    private boolean playAnimation = false;

    private Animation animation;


    public KeyItem(String id, String name, String description, double width, double height, boolean playAnimation) {
        super("Key", id, name, description, ItemStatus.NEW);

        this.playAnimation = playAnimation;

        this.width = width;
        this.height = height;


        Image image = new Image(getClass().getResourceAsStream("/Images/"+id.toLowerCase() + ".png"));

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setGraphic(imageView);

        //setText(id);

        // setStyle("-fx-background-color:transparent");
        setStyle("-fx-background-color:transparent;");
        // "-fx-border-color:black;-fx-border-width:5px;-fx-border-radius:10");
        setPrefSize(width, height);
        setEffect(Effects.GLOW());
        animation = pulseAnim(1000);
        if (playAnimation)
            animation.play();
        else animation.stop();

    }

    public KeyItem(String id, String name, String description, boolean playAnimation) {
        super("Key", id, name, description, ItemStatus.NEW);

        name = "door01";
        width = 20.0;
        height = 20.0;
        this.playAnimation = playAnimation;
        Image image = new Image(getClass().getClassLoader().getResource("images/" + id.toLowerCase() + ".png").getFile());


        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        setGraphic(imageView);
        setStyle("-fx-background-color:transparent;");
        // "-fx-border-color:black;-fx-border-width:5px;-fx-border-radius:10");
        setPrefSize(width, height);
        setEffect(Effects.GLOW());
        animation = pulseAnim(1000);
        if (playAnimation)
            animation.play();
        else animation.stop();
    }

    public boolean isPlayAnimation() {
        return playAnimation;
    }

    public void setPlayAnimation(boolean playAnimation) {
        this.playAnimation = playAnimation;
        if (!playAnimation)
            animation.stop();

    }

    @Override
    public Object use() {
        String usage = "doorWith"+ID;
        return usage.toUpperCase();
    }

    @Override
    public String getImage() {
        return "/Images/" + ID.toLowerCase() + ".png";

    }


}
