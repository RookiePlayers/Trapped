package inventory;


import javax.swing.JOptionPane;

import inventory.Models.*;
import inventory.UI.InventoryUI;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Runner extends Application {
    private InventoryUI inventoryMenu;
    private Item item = null;
    private int hps = 50, spd = 0, sec = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //  Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        int invRow = 4;
        KeyItem key = new KeyItem("gold_key", "Gold key", "", 50, 50, true);
        key.setPlayAnimation(true);
        HealthPotion hp = new HealthPotion("healthpotion", "+50 HP", "Adds 50 HP", 50, 50, true, 50);
        hp.setPlayAnimation(true);

        SpeedPotion sp = new SpeedPotion("speedpotion", "x2 Speed", "Doubles your Speed", 50, 50, true, 2);
        sp.setPlayAnimation(true);

        TimeFreeze tf = new TimeFreeze("timefreeze", "Time Freeze 20 ", "Freezes time for 20 seconds", 50, 50, true, 20);
        tf.setPlayAnimation(true);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(key);
        items.add(hp);
        items.add(tf);
        items.add(sp);
        Label health = new Label("HP: " + hps);
        health.setTextFill(Color.GREEN);
        health.setFont(new Font(12));
        Label speed = new Label("Spped: Normal");
        speed.setTextFill(Color.GREEN);
        speed.setFont(new Font(12));
        HBox player = new HBox();
        player.getChildren().addAll(health, speed);
        player.setAlignment(Pos.CENTER);
        player.setSpacing(12.0);

        Door doorA = new Door("gold_key", "Gold_Door", "", 80.0, 170.0);
        HBox itemBox = new HBox();

        Group root = new Group();
        for (Item it : items)
            itemBox.getChildren().add(it);
        root.getChildren().add(itemBox);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(root);
        borderPane.setBottom(doorA);
        borderPane.setTop(player);

        Button consume = new Button("CONSUME");

        borderPane.setRight(consume);
        Scene scene = new Scene(borderPane, 400, 400);
        inventoryMenu = new InventoryUI(scene, invRow);
        consume.setOnAction(e -> {
            if (item != null) {
                if (item.getType().equalsIgnoreCase("HP")) {
                    HealthPotion h = (HealthPotion) item;
                    if (hps < 100)
                        hps = hps + (int) h.use();
                    health.setText("HP: " + hps + "");
                    inventoryMenu.dropItem(h);
                    scene.setCursor(Cursor.DEFAULT);
                } else if (item.getType().equalsIgnoreCase("SP")) {
                    SpeedPotion s = (SpeedPotion) item;
                    s.setBoost(2);
                    if (spd < 4)
                        spd = spd + (int) s.use();
                    speed.setText("Speed: x" + spd + "");
                    inventoryMenu.dropItem(s);
                    scene.setCursor(Cursor.DEFAULT);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    if (sec > 0)
                                        sec--;
                                    else {
                                        break;
                                    }
                                    speed.setText("Speed: x" + spd + "[" + sec + "]");
                                    Thread.sleep(1000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    });

                }
            }
        });
        Label invLabel = new Label(inventoryMenu.getItemCount() + "");

        System.out.println(inventoryMenu.isFull());
        for (Item it : items)
            it.setOnAction(e -> {
                invLabel.setText(inventoryMenu.getItemCount() + 1 + "");

                // it.setPlayAnimation(false);
                if (!inventoryMenu.isFull()) {
                    it.saveItem();



                    itemBox.getChildren().remove(it);
                    inventoryMenu = new InventoryUI(scene, invRow);

                } else {


                }
            });


        scene.getStylesheets().add(getClass().getResource("/css/bag.css").toExternalForm());
        ImageView inventory_img = new ImageView(new Image(getClass().getResourceAsStream("/Images/inventory.png")));
        inventory_img.setFitHeight(60);
        inventory_img.setFitWidth(70);
        HBox box = new HBox();

        Button inventory = new Button("", inventory_img);
        box.getChildren().addAll(invLabel, inventory);
        box.setAlignment(Pos.TOP_CENTER);
        inventory.getStyleClass().add("inventoryBag");
        inventory.setAlignment(Pos.TOP_RIGHT);
        inventory.setEffect(Effects.DROP_SHADOW());
        inventory.setOnAction(e -> {
            scene.setCursor(Cursor.DEFAULT);
            item = null;
            inventoryMenu.showAndWait();
            item = inventoryMenu.getItem();
            invLabel.setText(inventoryMenu.getItemCount() + "");
            System.out.println("After: " + item);
        });
        borderPane.setLeft(box);
        doorA.setOnAction(e -> {
            if (item != null) {
                if (item.getType().equalsIgnoreCase("KEY"))
                    if (doorA.openDoor((KeyItem) item)) {
                        scene.setCursor(Cursor.DEFAULT);
                        inventoryMenu.dropItem(item);
                        Animation anim = doorA.open(1000);
                        anim.play();
                        anim.onFinishedProperty().set(after -> {
                            borderPane.getChildren().remove(doorA);


                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Target\nUse Item: " + doorA.getID());
                    }
            }
        });

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}